package com.mc.resourcesshareback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.resourcesshareback.interpretation.CheckLoginState;
import com.mc.resourcesshareback.pojo.ChatMessage;
import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.service.ChatMessageService;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
       
    }

    @CheckLoginState
    @PostMapping("getChatHistory")
    public Result<List<ChatMessage>> getChatHistory(@RequestParam("receiverId") Long receiverId,
            HttpServletRequest request) {

        if (chatMessageService.getChatHistory(ThreadLocalUtil.getLongId(), receiverId) != null) {
            return Result.success(chatMessageService.getChatHistory(ThreadLocalUtil.getLongId(), receiverId));
        }

        return Result.error(1111, "历史记录获取失败");
    }

    /**
     * 私信
     * 
     * @return
     */
    @CheckLoginState
    @PostMapping("send")
    public Result<String> privateLetter(@RequestBody ChatMessage chatMessage, HttpServletRequest request) {

        boolean send = chatMessageService.send(chatMessage);
        if (send) {
            return Result.success();
        }

        return Result.error(333,"发送失败");

    }

}
