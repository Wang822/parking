package com.parking.service;

import com.parking.dao.UserMapper;
import com.parking.pojo.User;
import java.util.List;

public class UserServiceImpl implements UserService{

    //service调dao层
    private UserMapper userMapper;


    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User queryUserByID(int id) {
        return userMapper.queryUserByID(id);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }
}
