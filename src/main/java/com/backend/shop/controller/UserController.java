package com.backend.shop.controller;

import com.backend.shop.common.GlobalResult;
import com.backend.shop.pojo.Account;
import com.backend.shop.pojo.User;
import com.backend.shop.service.IAccountService;
import com.backend.shop.service.IUserService;
import com.backend.shop.util.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Api(value = "User", description = "User Information")
public class UserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IAccountService iAccountService;

    @GetMapping("/get")
//    @ResponseBody
    @ApiOperation(value = "Get User's Information", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Information Success"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 204, message = "Student verify fail")})
    public ResponseEntity<User> getUser(@RequestHeader(value = "Authorization") String token) {
        int userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(user);
    }

    /**
     * @ RequestParam 注解
     * 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST
     *
     * @ RequestBody 注解
     * 该注解常用来处理Content-Type: 编码格式例如application/json, application/xml等
     */
    @PostMapping("/add")
//    @ResponseBody
    @ApiOperation(value = "Add User/Verify Student", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Add User Success"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 409, message = "Create User Repeat")})
    public ResponseEntity<User> addUser(@RequestHeader(value = "Authorization") String token,
                                @ApiParam("Note: do not need ID") @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //get User ID
        user.setUserId(userId);
        try {
            iUserService.save(user);
        } catch (Exception e) {
            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        //更新account authenticated
        Account account = new Account();
        account.setAccountId(userId);
        iAccountService.verify(account);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")
//    @ResponseBody
    @ApiOperation(value = "Update User Information", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update User Success"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 204, message = "Student verify fail")})
    public ResponseEntity<User> updateUser(@RequestHeader(value = "Authorization") String token,
                                        @ApiParam("Note: do not need ID") @RequestBody User user) {
        int userId = TokenUtil.getUserId(token); //get User ID

        User exist = iUserService.getById(userId);
        if (exist == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        user.setUserId(userId);
        iUserService.updateById(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getOther")
//    @ResponseBody
    @ApiOperation(value = "Get other user's information", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Information Success"),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 204, message = "Student verify fail")})
    public ResponseEntity<User> getOtherUser( @ApiParam(value = "User ID", example = "8") @RequestParam() int userId) {
//        int userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(user);
    }
}