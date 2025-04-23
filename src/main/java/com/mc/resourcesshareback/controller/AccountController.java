package com.mc.resourcesshareback.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mc.resourcesshareback.pojo.LoginInformation;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.pojo.User;
import com.mc.resourcesshareback.service.UserService;
import com.mc.resourcesshareback.utils.LogUtil;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册
     * 
     * @param user 需有username,email,password
     * @return Result类
     */
    @PostMapping("register")
    public Result<String> register(@Validated @RequestBody User user) {

        if (user.getEmail() == null || user.getPassword() == null) {
            return Result.error(1109, "error");
        }

        if (user.getUsername().isEmpty() || user.getUsername() == null) {
            user.setUsername("无名");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 20) {
            return Result.error(1110, "password length");
        }

        if (userService.register(user)) {
            LogUtil.info(getClass(), "用户：{}注册成功", user.getEmail());
        }
        return Result.success();

    }

    @PostMapping("login")
    public Result<Map<String, Object>> login(@Validated @RequestBody LoginInformation loginMessage) {

        String email = loginMessage.getEmail();
        String password = loginMessage.getPassword();

        if (email == null || password == null) {
            return Result.error(1109, "error");
        }
        LogUtil.info(getClass(), "用户：{}于{}登陆", email, LocalDateTime.now());
        return userService.login(loginMessage);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}