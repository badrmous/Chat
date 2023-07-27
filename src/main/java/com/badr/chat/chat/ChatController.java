package com.badr.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


    @MessageMapping("chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chat
    ){
        return chat;
    }


    @MessageMapping("chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chat,
            SimpMessageHeaderAccessor messageHeaderAccessor
    ){
        messageHeaderAccessor.getSessionAttributes().put("username",chat.getSender());
        return chat;
    }
}
