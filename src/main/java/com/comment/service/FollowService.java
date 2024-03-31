package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.Follow;

/**
 *
 *  服务类
 
 *    
 */
public interface FollowService extends IService<Follow> {

    Result follow(Long followUserId, Boolean isFollow);

    Result isFollow(Long followUserId);

    Result followCommons(Long id);
}
