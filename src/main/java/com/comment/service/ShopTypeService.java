package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.ShopType;

/**
 *
 *  服务类
 
 *    
 */
public interface ShopTypeService extends IService<ShopType> {
    Result queryList();
}
