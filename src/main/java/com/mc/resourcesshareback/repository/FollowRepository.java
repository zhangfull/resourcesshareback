package com.mc.resourcesshareback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.resourcesshareback.pojo.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long>{

    Follow findByUserFollowAndFollowUser(Long userFollow, Long followUser);
    void deleteByUserFollowAndFollowUser(Long userFollow, Long followUser);

}
