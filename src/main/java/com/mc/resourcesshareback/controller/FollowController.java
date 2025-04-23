package com.mc.resourcesshareback.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.resourcesshareback.interpretation.CheckLoginState;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.service.UserService;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/follow")
public class FollowController {

    private final UserService userService;

    public FollowController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 关注
     * 
     * @return
     */
    @PostMapping("exeFollower")
    @CheckLoginState
    public Result<Boolean> exeFollower(@RequestParam("userFollowId") Long userFollowerId, HttpServletRequest request) {

        String flag = userService.exeFollow(ThreadLocalUtil.getLongId(), userFollowerId);
        if (flag.equals("success")) {
            return Result.success();
        } else {
            return Result.error(333,flag);
        }
        
    }

}
