package com.mc.resourcesshareback.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "follow_table")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_follow")
    private Long userFollow;
    @Column(name = "follow_user")
    private Long followUser;
    @Column(name = "follow_time")
    private LocalDateTime followTime;

    @PrePersist
    public void setDefaultFollowTime() {
        if (this.followTime == null) {
            this.followTime = LocalDateTime.now(); // 只有在没有设置时才使用当前时间
        }
    }

}
