package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.Shop;

/**
 *
 *  服务类
 
 *    
 */
public interface ShopService extends IService<Shop> {

    Result queryById(Long id);

    Result update(Shop shop);

    Result queryShopByType(Integer typeId, Integer current);
}
