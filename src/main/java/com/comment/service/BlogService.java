package com.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.comment.common.Result;
import com.comment.model.entity.Blog;

/**
 *
 *  服务类

 *    
 */
public interface BlogService extends IService<Blog> {

    Result queryHotBlog(Integer current);

    Result queryBlogById(Long id);

    Result likeBlog(Long id);

    Result queryBlogLikes(Long id);

    Result saveBlog(Blog blog);

    Result queryBlogOfFollow(Long max, Integer offset);

}
