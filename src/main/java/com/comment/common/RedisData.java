package com.comment.common;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 逻辑过期时间实体类，用于需要设置逻辑过期字段的实体类
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
