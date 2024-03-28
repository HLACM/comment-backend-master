package com.comment.controller;


import com.comment.model.dto.Result;
import com.comment.service.FollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 粉丝业务
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private FollowService followService;

    @PutMapping("/{id}/{isFollow}")
    public Result follow(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow) {
        return followService.follow(followUserId, isFollow);
    }

    @GetMapping("/or/not/{id}")
    public Result isFollow(@PathVariable("id") Long followUserId) {
        return followService.isFollow(followUserId);
    }

    @GetMapping("/common/{id}")
    public Result followCommons(@PathVariable("id") Long id){
        return followService.followCommons(id);
    }
}
