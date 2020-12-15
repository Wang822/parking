package com.backend.shop.serviece.Impl;

import com.backend.shop.dao.ChatDAO;
import com.backend.shop.pojo.Chat;
import com.backend.shop.serviece.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDAO chatDAO;

    @Override
    public void addNewChat(Chat chat) {
        chatDAO.add(chat);
    }
}
