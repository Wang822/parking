package com.example.demo.controller;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;




    //增加用户信息
    //@CrossOrigin(origins = {"http://localhost:8083"}) //前端端口
    @GetMapping(path = "/AddUser")
    public @ResponseBody
    String addNewUser(@RequestParam("username") String username, @RequestParam("password") String password) {

        User n = new User();
        //n.setUserId(userid);
        n.setPassword(password);
        n.setUserName(username);
        n.setAccount(500);
        userRepository.save(n);
        return "Saved";
        //return n;
    }



    //迭代查询所有用户信息
    @GetMapping(path = "/AllUser")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll(Sort.by("userid"));

    }



    //按照id查询用户信息
    @CrossOrigin(origins = {"http://localhost:8083"})
    @GetMapping(path = "/GetUserById")
    public @ResponseBody
        //请求参数id映射绑定函数参数id,函数参数与数据库参数已在实体通过注解映射绑定
    User getUserById(@RequestParam("userid") Integer userid) {

        // This returns a JSON or XML with the users
        return userRepository.findById(userid).get();

    }





    //按照id删除用户
    @GetMapping(path = "/DeleteUserById")
    public @ResponseBody
    void delUser(@RequestParam("userid") Integer userid) {

        userRepository.deleteById(userid);

    }



    //通过id对name字段进行更新
    @GetMapping(path = "/UpdateUserName")
    public @ResponseBody
    void updateUser(@RequestParam("userid") Integer userid,@RequestParam("username") String username) {

        userRepository.updateNameById(userid,username);

    }

}




