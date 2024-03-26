package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.model.dto.Result;
import com.comment.model.entity.VoucherOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 *   
 *    
 */
public interface IVoucherOrderService extends IService<VoucherOrder> {

    Result seckillVoucher(Long voucherId);
}
