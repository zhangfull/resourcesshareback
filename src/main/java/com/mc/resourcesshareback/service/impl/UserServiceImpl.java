package com.mc.resourcesshareback.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mc.resourcesshareback.exception.OperationException;
import com.mc.resourcesshareback.mapper.UserMapper;
import com.mc.resourcesshareback.pojo.Follow;
import com.mc.resourcesshareback.pojo.LoginInformation;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.pojo.User;
import com.mc.resourcesshareback.repository.FollowRepository;
import com.mc.resourcesshareback.service.UserService;
import com.mc.resourcesshareback.utils.JwtUtil;
import com.mc.resourcesshareback.utils.LogUtil;
import com.mc.resourcesshareback.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {

    private final FollowRepository followRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, FollowRepository followRepository) {
        this.userMapper = userMapper;
        this.followRepository = followRepository;
    }

    /**
     * @return email:该邮箱已注册！ password:密码必须大于8位！ error:注册失败，请重试！
     */
    @Transactional
    @Override
    public boolean register(User user) {

        if (userMapper.getUserByEmail(user.getEmail()) != null) {
            throw new OperationException(1101, "email:"+user.getEmail() + "==>邮箱已注册");
        }

        String password = PasswordUtils.encrypt(user.getPassword());
        user.setPassword(password);
        if (userMapper.insertUser(user) == 1) {
            return true;
        }
        throw new OperationException(1108, "email:"+user.getEmail() +"==>注册失败（未知错误）");
    }

    /**
     * @return email: 没有注册！ password: 密码错误！ error: 登陆失败，请重试！
     */
    @Override
    public Result<Map<String, Object>> login(LoginInformation loginInformation) {
        User user = userMapper.getUserByEmail(loginInformation.getEmail());
        if (user == null) {
            return Result.error(1102, "用户未注册");
        }

        if (!PasswordUtils.verify(loginInformation.getPassword(), user.getPassword())) {
            return Result.error(1002,"登陆密码错误");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("id", user.getId());
        String token = JwtUtil.getToken(map);

        map.clear();

        map.put("token", token);
        map.put("username", user.getUsername());
        map.put("avatarUrl", user.getAvatarUrl());
        return Result.success(map);

    }

    @Override
    public User provideUserInformation(Long id) {
        User user = userMapper.getUserById(id);
        user.setPassword("null");
        return user;
    }

    @Override
    @Transactional
    public String exeFollow(Long userFollow, Long followUser) {

        if (followRepository.findByUserFollowAndFollowUser(userFollow, followUser) != null) {
            // 已关注，执行取关操作
            if (userMapper.getUserByIdForUpdate(followUser) == null) {
                throw new OperationException(1104, "用户:[" + userFollow + "]->["+followUser+"]==>关注对象不存在");
            }

            if (userMapper.reduceFollower(followUser) != 1) {
                throw new OperationException(1105,  "用户:[" + userFollow + "]->["+followUser+"]==>取关数据库修改失败");
            }
            followRepository.deleteByUserFollowAndFollowUser(userFollow, followUser);
            LogUtil.info(getClass(), "{}test", 1000);
        } else {
            // 未关注，执行关注操作
            if (userMapper.getUserByIdForUpdate(followUser) == null) {
                throw new OperationException(1106, "用户:[" + userFollow + "]->["+followUser+"]==>要关注的对象不存在");
            }
            Follow follow = new Follow();
            follow.setUserFollow(userFollow);
            follow.setFollowUser(followUser);
            if (userMapper.increaseFollower(followUser) != 1) {
                throw new OperationException(1107, "用户:[" + userFollow + "]->["+followUser+"]==>关注数据库修改失败");
            }
            followRepository.save(follow);

        }
        return "success";

    }

    @Transactional
    @Override
    public boolean setNewName(Long userId, String name) {
        if (userMapper.updateUserName(userId, name) == 1) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean setNewAvatar(Long userId, String avatarUrl) {
        if (userMapper.updateUserAvatar(userId, avatarUrl) == 1) {
            return true;
        }
        return false;
    }

}
