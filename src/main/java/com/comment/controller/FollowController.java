package com.comment.controller;


import com.comment.common.Result;
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

    /**
     * 关注用户
     * @param followUserId 粉丝id
     * @param isFollow 是否已关注
     * @return
     */
    @PutMapping("/{id}/{isFollow}")
    public Result follow(@PathVariable("id") Long followUserId, @PathVariable("isFollow") Boolean isFollow) {
        return followService.follow(followUserId, isFollow);
    }

    /**
     * 是否已关注对应用户
     * @param followUserId 对应用户id
     * @return
     */
    @GetMapping("/or/not/{id}")
    public Result isFollow(@PathVariable("id") Long followUserId) {
        return followService.isFollow(followUserId);
    }

    /**
     * 展示共同关注功能
     * @param id 对应用户id
     * @return
     */
    @GetMapping("/common/{id}")
    public Result followCommons(@PathVariable("id") Long id){
        return followService.followCommons(id);
    }
}
