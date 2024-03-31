package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.model.dto.LoginFormDTO;
import com.comment.common.Result;
import com.comment.model.entity.User;

import javax.servlet.http.HttpSession;

/**
 *
 *  服务类
 
 *    
 */
public interface UserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();

}
