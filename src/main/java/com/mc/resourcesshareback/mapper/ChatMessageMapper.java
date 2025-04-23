package com.mc.resourcesshareback.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mc.resourcesshareback.pojo.ChatMessage;

@Mapper
public interface ChatMessageMapper {

    int deleteOldMessages();

    List<ChatMessage> getChatHistory(@Param("senderId") Long senderId, @Param("receiverId")Long receiverId);

}
