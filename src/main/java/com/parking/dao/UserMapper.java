package com.parking.dao;

import com.parking.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //增加用户
    int addUser(User user);

    //删除用户
    int deleteUserById(@Param("userid") int id);

    //修改用户
    int updateUser(User user);

    //查询用户
    User queryUserByID(@Param("userid") int id);

    //查询全部用户
    List<User> queryAllUser();
}
