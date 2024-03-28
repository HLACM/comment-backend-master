package com.comment;

import cn.hutool.json.JSONUtil;
import com.comment.constant.RabbitmqConstant;
import com.comment.model.entity.VoucherOrder;
import com.comment.service.VoucherOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class VoucherOrderServiceImplTest {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void createVoucherOrder() {

    }


    @Test
    void seckillVoucher() {
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(110L);
        voucherOrder.setUserId(1L);
        voucherOrder.setVoucherId(1L);
        rabbitTemplate.convertAndSend(RabbitmqConstant.SECKILL_VOUCHER_EXCANGE,voucherOrder);
        String s = JSONUtil.toJsonStr(voucherOrder);
        System.out.println(s);
    }
}