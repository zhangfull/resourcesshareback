package com.mc.resourcesshareback.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Data 
@Table(name = "task_entity")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;
    @Column(name = "params")
    private String params;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void setDefaultFollowTime() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // 只有在没有设置时才使用当前时间
        }
    }

  
}
