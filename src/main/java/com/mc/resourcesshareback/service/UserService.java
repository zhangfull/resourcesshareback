package com.mc.resourcesshareback.service;

import java.util.Map;

import com.mc.resourcesshareback.pojo.LoginInformation;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.pojo.User;

public interface UserService {

    boolean register(User user);

    Result<Map<String, Object>> login(LoginInformation loginInformation);

    User provideUserInformation(Long id);

    String exeFollow(Long userFollow, Long followUser);

    boolean setNewName(Long userId, String name);

    boolean setNewAvatar(Long userId, String avatarUrl);

}
