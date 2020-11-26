package com.parking.controller;


import com.parking.pojo.User;
import com.parking.service.UserService;
import com.parking.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    //controller 调service
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    //查询全部书籍
    @RequestMapping("/allUser")
    public List<User> list(Model model){
        List<User> list=userService.queryAllUser(); //查询所有数据
        model.addAttribute("list",list);//把数据都放到目录里面
        System.out.println(list);
        return list;
    }

    //增加用户
    @RequestMapping("/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "success";
    }

    //修改用户
    @RequestMapping("/updateUser")
    public String updateUser(Model model,User user){
        userService.updateUser(user);
        User user1 = userService.queryUserByID(user.getUserid());
        model.addAttribute("user", user1);
        return "success";
    }

    //删除用户
    @RequestMapping("/deleteUser/{userid}")
    public String deleteUser(@PathVariable("userid")int id){
        userService.deleteUserById(id);
        return "success";
    }

    //查询用户
    @RequestMapping("/queryUser/{userid}")
    @ResponseBody
    public User queryUser(@PathVariable("userid")int id){
        User user1=userService.queryUserByID(id);
        System.out.println(user1);
        return user1;

    }




}
