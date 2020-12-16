package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Chat;
import com.backend.shop.pojo.User;
import com.backend.shop.serviece.ChatService;
import com.backend.shop.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * add a new message
     *
     * @param token       token
     * @param receiver_id user id of receiver
     * @param content     message content
     * @param time        sending time, pattern: yyyy-MM-dd hh:mm:ss
     * @return GlobalResult
     * @throws ParseException time pattern error
     */
    @PostMapping("/addMessage")
    public GlobalResult addChat(@RequestHeader(value = "Authorization") String token, @RequestParam int receiver_id,
                                @RequestParam String content,
                                @RequestParam String time) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        Chat chat = new Chat();
        chat.setReceiverId(receiver_id);
        chat.setSenderId(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(time);
        chat.setTime(date);
        chat.setContent(content);
        chatService.add(chat);
        return new GlobalResult(200, "successful operation");
    }

    @GetMapping("/getContacts")
    public GlobalResult getContacts(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<User> users = chatService.selectContacts(userId);
        return new GlobalResult(200, "successful operation", users);
    }

    @GetMapping("getMessages/{anotherUserId}")
    public GlobalResult getMessages(@RequestHeader(value = "Authorization") String token,
                                    @PathVariable int anotherUserId) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<Chat> chats = chatService.selectMessages(userId, anotherUserId);
//        if(chats.isEmpty()){
//            return new GlobalResult(401, "no messages between these two users");
//        }
        return new GlobalResult(200, "successful operation", chats);
    }
}

