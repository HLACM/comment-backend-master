package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.VoucherOrder;

/**
 *  服务类
 */
public interface VoucherOrderService extends IService<VoucherOrder> {

    Result seckillVoucher(Long voucherId);

    void createVoucherOrder(VoucherOrder voucherOrder);
}
