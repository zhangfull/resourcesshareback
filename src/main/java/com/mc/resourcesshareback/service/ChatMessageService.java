package com.mc.resourcesshareback.service;

import java.util.List;

import com.mc.resourcesshareback.pojo.ChatMessage;

public interface ChatMessageService {

    void automaticallyDeleteHistory();

    List<ChatMessage> getChatHistory(Long senderId, Long receiverId);

    boolean send(ChatMessage chatMessage);

    boolean deleteOldMessagesById(Long id);

}
