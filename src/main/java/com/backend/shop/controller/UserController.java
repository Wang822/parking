package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.User;
import com.backend.shop.serviece.IUserService;
import com.backend.shop.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping("user/get")
    @ResponseBody
    public GlobalResult getUser(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        if (user == null) {
            return new GlobalResult(204, "此用户未完成学生认证");
        }
        return new GlobalResult(200, "成功读取用户信息", user);
    }

    /**
     * @ RequestParam 注解
     * 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST
     *
     * @ RequestBody 注解
     * 该注解常用来处理Content-Type: 编码格式例如application/json, application/xml等
     */
    @PostMapping("user/addOrUpdate")
    @ResponseBody
    public GlobalResult addUser(@RequestHeader(value = "Authorization") String token,
                                @RequestBody User user) {
        user.setUserId(TokenUtil.getUserId(token));
        iUserService.saveOrUpdate(user);
        return new GlobalResult(200, "成功修改用户信息", user);
    }

    /* shiro test, not API */
//    @PostMapping("users")
//    //@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//    @ResponseBody
//    public GlobalResult getUsers(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        System.out.println(token);
//        System.out.println(TokenUtil.getUserId(token));
//        return new GlobalResult(200, "success shiro");
//    }

}
