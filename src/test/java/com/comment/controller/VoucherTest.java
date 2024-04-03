package com.comment.controller;

import com.comment.model.entity.Voucher;
import com.comment.service.VoucherOrderService;
import com.comment.service.VoucherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VoucherTest {

    @Resource
    private VoucherService voucherService;

    @Test
    void addSeckillVoucher() {
        Voucher voucher=new Voucher();
        voucher.setId(2L);
        voucher.setShopId(1L);
        voucher.setTitle("100元秒杀券");
        voucher.setStock(100);
        voucher.setBeginTime(LocalDateTime.now());
        voucher.setEndTime(LocalDateTime.now().plusSeconds(1000000));
        voucher.setPayValue(4750L);
        voucher.setActualValue(5000L);
        voucherService.addSeckillVoucher(voucher);
    }
}