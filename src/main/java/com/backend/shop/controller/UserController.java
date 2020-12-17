package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Account;
import com.backend.shop.pojo.User;
import com.backend.shop.service.IAccountService;
import com.backend.shop.service.IUserService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Api(value = "User", description = "用户个人信息")
public class UserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IAccountService iAccountService;

    @GetMapping("/get")
    @ResponseBody
    @ApiOperation(value = "获取用户个人信息", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功读取用户信息"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 204, message = "此用户未完成学生认证")})
    public GlobalResult<User> getUser(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        if (user == null) {
            return new GlobalResult<>(204, "此用户未完成学生认证");
        }
        return new GlobalResult<>(200, "成功读取用户信息", user);
    }

    /**
     * @ RequestParam 注解
     * 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST
     *
     * @ RequestBody 注解
     * 该注解常用来处理Content-Type: 编码格式例如application/json, application/xml等
     */
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加用户/学生认证", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功添加用户"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 500, message = "重复创建用户")})
    public GlobalResult<User> addUser(@RequestHeader(value = "Authorization") String token,
                                @ApiParam("用户个人信息，不用ID") @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //获取User ID
        user.setUserId(userId);
        try {
            iUserService.save(user);
        } catch (Exception e) {
            //e.printStackTrace();
            return new GlobalResult<>(500, "重复创建用户");
        }

        //更新account authenticated
        Account account = new Account();
        account.setAccountId(userId);
        iAccountService.verify(account);
        return new GlobalResult<>(200, "成功添加用户", user);
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "修改用户个人信息", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功修改用户信息"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 204, message = "未完成学生认证")})
    public GlobalResult<User> updateUser(@RequestHeader(value = "Authorization") String token,
                                   @ApiParam("用户个人信息，不用ID") @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //获取User ID

        User exist = iUserService.getById(userId);
        if (exist == null) {
            return new GlobalResult<User>(204, "未完成学生认证");
        }
        user.setUserId(userId);
        iUserService.updateById(user);
        return new GlobalResult<User>(200, "成功修改用户信息", user);
    }

}
