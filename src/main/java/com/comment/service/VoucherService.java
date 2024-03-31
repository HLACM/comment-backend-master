package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.Voucher;

/**
 *
 *  服务类
 
 *    
 */
public interface VoucherService extends IService<Voucher> {

    Result queryVoucherOfShop(Long shopId);

    void addSeckillVoucher(Voucher voucher);
}
