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
@Table(name = "chat_message_table")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "receiver_id")
    private Long receiverId;
    @Column(name = "content")
    private String content;
    @Column(name = "sent_time")
    private LocalDateTime sentTime;

    @PrePersist
    public void setDefaultFollowTime() {
        if (this.sentTime == null) {
            this.sentTime = LocalDateTime.now(); // 只有在没有设置时才使用当前时间
        }
    }

}
