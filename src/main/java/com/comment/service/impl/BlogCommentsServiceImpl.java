package com.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comment.model.entity.BlogComments;
import com.comment.mapper.BlogCommentsMapper;
import com.comment.service.BlogCommentsService;
import org.springframework.stereotype.Service;

/**
 *
 *  服务实现类
 
 *    
 */
@Service
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements BlogCommentsService {

}
