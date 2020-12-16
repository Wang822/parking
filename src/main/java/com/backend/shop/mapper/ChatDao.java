package com.backend.shop.mapper;

import com.backend.shop.pojo.Chat;
import com.backend.shop.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ChatDao {
    void add(Chat chat);

    ArrayList<User> selectContacts(int userId);

    ArrayList<Chat> selectMessages(@Param("myUser") int userId, @Param("anotherUser") int anotherId);
}
