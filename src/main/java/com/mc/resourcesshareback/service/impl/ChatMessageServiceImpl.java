package com.mc.resourcesshareback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mc.resourcesshareback.mapper.ChatMessageMapper;
import com.mc.resourcesshareback.pojo.ChatMessage;
import com.mc.resourcesshareback.repository.ChatMessageRepository;
import com.mc.resourcesshareback.service.ChatMessageService;
import com.mc.resourcesshareback.utils.LogUtil;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.transaction.Transactional;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageMapper chatMessageMapper;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageMapper chatMessageMapper, ChatMessageRepository chatMessageRepository) {
        this.chatMessageMapper = chatMessageMapper;
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    @Scheduled(cron = "0 0 3 ? * MON") // 每周一凌晨3点
    public void automaticallyDeleteHistory() {
        LogUtil.info(getClass(), "已删除{}条历史聊天记录", chatMessageMapper.deleteOldMessages());
    }

    @Override
    public List<ChatMessage> getChatHistory(Long senderId, Long receiverId) {
        return chatMessageMapper.getChatHistory(senderId, receiverId);
    }

    @Transactional
    @Override
    public boolean send(ChatMessage chatMessage) {
        if (chatMessage.getReceiverId() == null) {
            return false;
        }

        chatMessage.setSenderId(ThreadLocalUtil.getLongId());

        // 尝试保存数据
        chatMessageRepository.save(chatMessage);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteOldMessagesById(Long id) {

        chatMessageRepository.deleteById(id);
        return true;

    }

}
