package com.comment.common;

import com.comment.model.dto.UserDTO;

/**
 * 该工具类用于在多线程环境中保存和获取用户信息。它使用了ThreadLocal来存储每个线程独有的UserDTO对象。
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    /**
     * 将用户信息存储到当前线程的ThreadLocal对象中
     * @param user
     */
    public static void saveUser(UserDTO user){
        tl.set(user);
    }
    public static UserDTO getUser(){
        return tl.get();
    }
    public static void removeUser(){
        tl.remove();
    }
}
