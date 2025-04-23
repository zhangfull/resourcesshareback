package com.mc.resourcesshareback.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mc.resourcesshareback.pojo.User;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    User getUserById(Long id);

    int updateUserName(@Param("id") Long id, @Param("name") String name);

    int updateUserAvatar(@Param("id") Long id, @Param("avatarUrl") String avatarUrl);

    int deleteUserById(Long id);

    int increaseFollower(Long id);

    int reduceFollower(Long id);

    User getUserByIdForUpdate(Long id);


}