package com.backend.shop.serviece.Impl;

import com.backend.shop.mapper.UserMapper;
import com.backend.shop.pojo.User;
import com.backend.shop.serviece.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Autowired
//    UserMapper userMapper;

}
