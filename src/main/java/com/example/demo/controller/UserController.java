package com.example.demo.controller;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/demo")
public class UserController {

    @Autowired
    private UserRepository userRepository;




    //增加用户信息
    //@CrossOrigin(origins = {"http://localhost:8083"}) //前端端口
    @PostMapping(path = "/AddUser")
    public @ResponseBody
    String addNewUser(@RequestParam("wx_id") String wx_id, @RequestParam("password") String password) {

        User n = new User();
        //n.setUserId(userid);
        n.setPassword(password);
        n.setWxId(wx_id);
        userRepository.save(n);
        return "Saved";
        //return n;
    }



    //迭代查询所有用户信息
    @PostMapping(path = "/AllUser")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll(Sort.by("user_id"));
    }



    //按照id查询用户信息
    //@CrossOrigin(origins = {"http://localhost:8083"})
    @PostMapping(path = "/GetUserById")
    public @ResponseBody
        //请求参数id映射绑定函数参数id,函数参数与数据库参数已在实体通过注解映射绑定
    User getUserById(@RequestParam("user_id") Integer user_id) {

        // This returns a JSON or XML with the users
        return userRepository.findById(user_id).get();

    }





    //按照id删除用户
    @PostMapping(path = "/DeleteUserById")
    public @ResponseBody
    void delUser(@RequestParam("user_id") Integer user_id) {

        userRepository.deleteById(user_id);

    }



    //通过id对name字段进行更新
    @PostMapping(path = "/UpdateUserName")
    public @ResponseBody
    void updateUser(@RequestParam("user_id") Integer user_id,@RequestParam("wx_id") String wx_id) {

        userRepository.updateNameByUser_id(user_id,wx_id);

    }

}




