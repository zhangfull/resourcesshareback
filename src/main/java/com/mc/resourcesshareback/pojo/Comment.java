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
@Table(name = "comment_table")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "resource_id")
    Long resourceId;
    @Column(name = "user_id")
     Long userId;
     @Column(name = "comment")
    String comment;
    @Column(name = "comment_time")
    LocalDateTime commentTime;

    @PrePersist
    public void setDefaultFollowTime() {
        if (this.commentTime == null) {
            this.commentTime = LocalDateTime.now(); // 只有在没有设置时才使用当前时间
        }
    }

}
