package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Chat;
import com.backend.shop.pojo.Good;
import com.backend.shop.pojo.User;
import com.backend.shop.service.ChatService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@Api(tags = "chat")
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
     * @return ResponseEntity
     * @throws ParseException time pattern error
     */
    @PostMapping("/addMessage")
    @ApiOperation(value = "add a new massage")
    public ResponseEntity<String> addChat(@RequestHeader(value = "Authorization") String token,
                                          @ApiParam(value = "receiver's id", example = "3") @RequestParam int receiver_id,
                                          @ApiParam(value = "message's content", example = "6") @RequestParam String content) throws ParseException {
        int userId = TokenUtil.getUserId(token);
        Chat chat = new Chat();
        chat.setReceiverId(receiver_id);
        chat.setSenderId(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        chat.setTime(sdf.format(new Date()));
        chat.setContent(content);
        chatService.add(chat);
        return ResponseEntity.status(HttpStatus.OK).body("successful operation");
    }

    @ApiOperation(value = "get users who have chatted with the user")
    @GetMapping("/getContacts")
    public ResponseEntity<ArrayList<User>> getContacts(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<User> users = chatService.selectContacts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @ApiOperation(value = "get all messages between two users", response = Chat.class)
    @GetMapping("getMessages/{anotherUserId}")
    public ResponseEntity<ArrayList<Chat>> getMessages(@RequestHeader(value = "Authorization") String token,
                                                       @ApiParam(value = "another user's id", example = "6") @PathVariable int anotherUserId) {
        int userId = TokenUtil.getUserId(token);
        ArrayList<Chat> chats = chatService.selectMessages(userId, anotherUserId);
//        if(chats.isEmpty()){
//            return new GlobalResult(401, "no messages between these two users");
//        }
        return ResponseEntity.status(HttpStatus.OK).body(chats);
    }
}

