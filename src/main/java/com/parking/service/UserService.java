package com.parking.service;

import com.parking.pojo.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    //增加用户
    int addUser(User user);

    //删除用户
    int deleteUserById(int id);

    //修改用户
    int updateUser(User user);

    //查询用户
    User queryUserByID(int id);

    //查询全部用户
    List<User> queryAllUser();
}
