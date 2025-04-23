package com.mc.resourcesshareback.controller;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mc.resourcesshareback.interpretation.CheckLoginState;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.pojo.User;
import com.mc.resourcesshareback.service.UserService;
import com.mc.resourcesshareback.utils.StockpileUtils;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @CheckLoginState
    @GetMapping("get")
    public Result<User> profile(HttpServletRequest request) {

        User user = userService.provideUserInformation(ThreadLocalUtil.getLongId());

        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error(333,"user is not exist");
        }
    }

    

    /**
     * 更新用户信息
     */

    private static final String STATIC_AVATARS = "D://mcproject/resourcesshareback/src/main/resources/static/avatars";

    @CheckLoginState
    @PostMapping("setAvatar")
    public Result<String> updataUserProfile(@RequestParam("image") MultipartFile image,
            HttpServletRequest request) {

        if (image == null) {
            return Result.error(333,"图片获取失败");
        }
        String userAvatarName = "avatar" + ThreadLocalUtil.getLongId().toString() + ".png";
        boolean result1 = StockpileUtils.stockpileImage(image, STATIC_AVATARS, userAvatarName);
        boolean result2 = userService.setNewAvatar(ThreadLocalUtil.getLongId(), userAvatarName);

        String deleteAvatarPath = Paths.get(STATIC_AVATARS, userAvatarName).toString();
        File deleteAvatar = new File(deleteAvatarPath);
        if (!result1 && result2) {
            StockpileUtils.deleteDirectoryAndContents(deleteAvatar);
            userService.setNewAvatar(ThreadLocalUtil.getLongId(), "default.png");
            return Result.error(333,"更改失败");
        }
        return Result.success();
    }

}
