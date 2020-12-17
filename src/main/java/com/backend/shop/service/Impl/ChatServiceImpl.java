package com.backend.shop.service.Impl;

import com.backend.shop.mapper.ChatDao;
import com.backend.shop.pojo.Chat;
import com.backend.shop.pojo.User;
import com.backend.shop.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    public void add(Chat chat) {
        chatDao.add(chat);
    }

    @Override
    public ArrayList<User> selectContacts(int userId) {
        return chatDao.selectContacts(userId);
    }

    @Override
    public ArrayList<Chat> selectMessages(int userId, int anotherId) {
        return chatDao.selectMessages(userId, anotherId);
    }
}
