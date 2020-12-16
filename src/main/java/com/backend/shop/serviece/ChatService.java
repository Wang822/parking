package com.backend.shop.serviece;

import com.backend.shop.pojo.Chat;
import com.backend.shop.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ChatService {
    void add(Chat chat);

    ArrayList<User> selectContacts(int userId);

    ArrayList<Chat> selectMessages(int userId, int anotherId);
}
