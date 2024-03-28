package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.model.dto.Result;
import com.comment.model.entity.ShopType;

/**
 * <p>
 *  服务类
 * </p>
 *
 *   
 *    
 */
public interface ShopTypeService extends IService<ShopType> {
    Result queryList();
}
