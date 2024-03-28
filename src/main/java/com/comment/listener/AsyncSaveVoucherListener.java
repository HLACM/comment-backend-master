package com.comment.listener;

import cn.hutool.json.JSONUtil;
import com.comment.constant.RabbitmqConstant;
import com.comment.model.entity.VoucherOrder;
import com.comment.service.SeckillVoucherService;
import com.comment.service.VoucherOrderService;
import com.comment.service.impl.VoucherOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

@Component
@Slf4j
public class AsyncSaveVoucherListener {
    @Resource
    private VoucherOrderService voucherOrderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitmqConstant.SECKILL_VOUCHER_QUEUE),
            exchange = @Exchange(name = RabbitmqConstant.SECKILL_VOUCHER_EXCANGE, type = ExchangeTypes.FANOUT)
    ))
    public void AsyncSave(VoucherOrder voucherOrder)
    {
        voucherOrderService.createVoucherOrder(voucherOrder);
    }
}
