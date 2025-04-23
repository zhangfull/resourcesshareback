package com.mc.resourcesshareback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.resourcesshareback.pojo.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
