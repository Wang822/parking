package com.backend.shop.controller;

import com.backend.shop.pojo.Chat;
import com.backend.shop.serviece.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    //    TODO:解决传两个参数的问题
//add new chat message
    @PostMapping("/addMessage")
    public String addChat(@RequestParam String receiver_id,
                          @RequestParam String content,
                          @RequestParam String time) {
        String sender_id = "1";

//
        return "200";
    }
}

