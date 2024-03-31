package com.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comment.model.entity.UserInfo;
import com.comment.mapper.UserInfoMapper;
import com.comment.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 *
 *  服务实现类
 *     
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
