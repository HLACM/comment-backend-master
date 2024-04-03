package com.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comment.constant.RabbitmqConstant;
import com.comment.common.Result;
import com.comment.constant.RedisConstants;
import com.comment.model.entity.VoucherOrder;
import com.comment.mapper.VoucherOrderMapper;
import com.comment.service.SeckillVoucherService;
import com.comment.service.VoucherOrderService;
import com.comment.utils.RedisIdWorker;
import com.comment.common.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

/**
 * 核心秒杀业务
 */
@Slf4j
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements VoucherOrderService {

    @Resource
    private SeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker redisIDworker;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;




    /**
     * 异步创建订单
     * CAS乐观锁法解决库存超卖问题（多线程情况下的更新问题），分布式锁解决一人一单问题（多线程情况下的插入问题）
     * @param voucherOrder
     */
    @Override
    public void createVoucherOrder(VoucherOrder voucherOrder) {
        Long userId = voucherOrder.getUserId();
        Long voucherId = voucherOrder.getVoucherId();
        // 创建锁对象
        RLock redisLock = redissonClient.getLock("lock:order:" + userId);
        // 尝试获取锁
        boolean isLock = redisLock.tryLock();
        // 判断
        if (!isLock) {
            // 获取锁失败，直接返回失败或者重试
            log.error("不允许重复下单！");
            return;
        }
        try {
            // 5.1.查询订单
            int count = query().eq("user_id", userId).
                    eq("voucher_id", voucherId).count();
            // 5.2.判断是否存在
            if (count > 0) {
                // 用户已经购买过了
                log.error("不允许重复下单！");
                return;
            }

            // 6.扣减库存，使用CAS乐观锁思想解决了多线程情况下的库存超卖问题，执行操作前再次判断库存是否大于0
            boolean success = seckillVoucherService.update()
                    .setSql("stock = stock - 1") // set stock = stock - 1
                    .eq("voucher_id", voucherId).gt("stock", 0)
                    // where id = ? and stock > 0
                    .update();
            if (!success) {
                // 扣减失败
                log.error("库存不足！");
                return;
            }
            // 7.创建订单
            save(voucherOrder);
        } finally {
            // 释放锁
            redisLock.unlock();
        }
    }


    @Override
    @Transactional
    public Result seckillVoucher(Long voucherId) {
//        1,执行lua脚本,lua脚本用于判断库存是否充足,扣库存操作
        Long userId=UserHolder.getUser().getId();
        long orderId=redisIDworker.nextId("order");


        String stockKey= RedisConstants.SECKILL_STOCK_KEY+voucherId;
        //判断redis的中该优惠券是否存在对应用户的购买记录（判断是否存在即可，因为是用set存储）的key
        String orderKey=RedisConstants.SECKILL_ORDER_KEY+voucherId;

        Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get(stockKey));
        if (stock<=0){
            return Result.fail("库存不足");
        }
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(orderKey, userId);
        //存在，说明是重复下单
        if (isMember){
            return Result.fail("不能重复下单");
        }
        //扣减库存
        stringRedisTemplate.opsForValue().increment(stockKey,-1);
        //保存用户到Redis中（已购买）
        stringRedisTemplate.opsForSet().add(orderKey,userId.toString());

        //异步写数据库
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        rabbitTemplate.convertAndSend(RabbitmqConstant.SECKILL_VOUCHER_EXCANGE,voucherOrder);
        log.info("发送保存秒杀券订单信息成功:{}",orderId);
        return Result.ok("seckSuccess");
    }



}
