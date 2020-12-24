package com.backend.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.backend.shop.common.GlobalResult;
import com.backend.shop.util.RedisUtil;
import com.backend.shop.util.TokenUtil;
import com.backend.shop.util.WechatUtil;
import com.backend.shop.pojo.Account;
import com.backend.shop.service.IAccountService;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
@Api(value = "Account", description = "Account")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    /**
     * 微信用户登录详情
     */
    @ApiOperation("注册或登录")
    @PostMapping("wx/login")
    @ResponseBody
    @ApiResponses({
            @ApiResponse(code = 200, message = "Login Success, return whether student verify passed", responseHeaders = {
                    @ResponseHeader(name = "Authorization", description = "login token")}),
            @ApiResponse(code = 401, message = "token verify fail"),
            @ApiResponse(code = 403, message = "Signature verify fail")})
    public ResponseEntity<Boolean> user_login(
                                   @ApiParam("temporary login credential code")    @RequestParam(value = "code") String code,
                                   @ApiParam("user non-sensitive information")  @RequestParam(value = "rawData") String rawData,
                                   @ApiParam("signature")          @RequestParam(value = "signature") String signature,
                                   @ApiParam("user sensitive information")    @RequestParam(value = "encrypteData") String encrypteData,
                                   @ApiParam("decryption algorithm vector")    @RequestParam(value = "iv") String iv,
                                   HttpServletResponse response) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
//            return new GlobalResult<>(403, "签名校验失败");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        Account account = this.iAccountService.getOneByOpenId(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
//        String skey = UUID.randomUUID().toString();
        if (account == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatar = rawDataJson.getString("avatar");

            account = new Account();
            account.setOpenId(openid);
            account.setCreateTime(new Date());
            account.setLastVisitTime(new Date());
            account.setNickName(nickName);
            account.setAvatar(avatar);
            account.setAuthenticated(false);
            this.iAccountService.save(account);
        } else {
            // 已存在，更新用户登录时间
            account.setLastVisitTime(new Date());
            // 重新设置会话skey
//            account.setSkey(skey);
            this.iAccountService.updateById(account);
        }
        //6. 把新的skey返回给小程序
//        GlobalResult result = GlobalResult.build(200, null, skey);

        //Integer ID = account.getAccountId();
        String accountId = String.valueOf(account.getAccountId());
        // 生成token
        long currentTimeMillis = System.currentTimeMillis();
        String token = TokenUtil.sign(accountId, currentTimeMillis);
//        RedisUtil.set(accountId, account, currentTimeMillis); //, TokenUtil.REFRESH_EXPIRE_TIME
        RedisUtil.set(accountId, currentTimeMillis, TokenUtil.REFRESH_EXPIRE_TIME);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        System.out.println(new Date() + "  [Login]   AccountID: " + accountId);

        return ResponseEntity.ok(account.isAuthenticated());
    }

}
