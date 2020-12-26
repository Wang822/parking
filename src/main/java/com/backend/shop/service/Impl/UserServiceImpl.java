package com.backend.shop.service.Impl;

import com.backend.shop.mapper.UserDao;
import com.backend.shop.pojo.User;
import com.backend.shop.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

//    @Autowired
//    UserMapper userMapper;

}
