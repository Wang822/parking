package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Account;
import com.backend.shop.pojo.User;
import com.backend.shop.serviece.IAccountService;
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

    @Autowired
    IAccountService iAccountService;

    @GetMapping("user/get")
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
    @PostMapping("user/add")
    @ResponseBody
    public GlobalResult addUser(@RequestHeader(value = "Authorization") String token,
                                @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //获取User ID
//        User exist = iUserService.getById(userId);
//        if (exist != null) {
//            return new GlobalResult(500, "重复创建用户", exist);
//        }
        user.setUserId(userId);
        try {
            iUserService.save(user);
        } catch (Exception e) {
            //e.printStackTrace();
            return new GlobalResult(500, "重复创建用户");
        }

        //更新account authenticated
        Account account = new Account();
        account.setAccountId(userId);
        iAccountService.verify(account);
        return new GlobalResult(200, "成功修改用户信息", user);
    }

    @PostMapping("user/update")
    @ResponseBody
    public GlobalResult updateUser(@RequestHeader(value = "Authorization") String token,
                                @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //获取User ID

        User exist = iUserService.getById(userId);
        if (exist == null) {
            return new GlobalResult(204, "未完成学生认证");
        }
        user.setUserId(userId);
        iUserService.updateById(user);
//        try {
//            iUserService.updateById(user);
//        } catch (Exception e) {
//            return new GlobalResult(204, "未完成学生认证");
//        }
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
