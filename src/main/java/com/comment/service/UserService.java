package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.model.dto.LoginFormDTO;
import com.comment.model.dto.Result;
import com.comment.model.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 *   
 *    
 */
public interface UserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();

}
