package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.model.dto.Result;
import com.comment.model.entity.Voucher;

/**
 * <p>
 *  服务类
 * </p>
 *
 *   
 *    
 */
public interface VoucherService extends IService<Voucher> {

    Result queryVoucherOfShop(Long shopId);

    void addSeckillVoucher(Voucher voucher);
}
