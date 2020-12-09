package com.backend.parking.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.backend.parking.common.GlobalResult;
import com.backend.parking.common.WechatUtil;
import com.backend.parking.entity.Account;
import com.backend.parking.mapper.AccountMapper;
import com.backend.parking.service.impl.AccountServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;


@Controller
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;
//    private AccountServiceImpl accountService;

    /**
     * 微信用户登录详情
     */
    @PostMapping("wx/login")
    @ResponseBody
    public GlobalResult user_login(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "rawData", required = false) String rawData,
                                   @RequestParam(value = "signature", required = false) String signature,
                                   @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                   @RequestParam(value = "iv", required = false) String iv) {
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
            return GlobalResult.build(500, "签名校验失败", null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        //User user = this.userMapper.selectById(openid);
        Account account = this.accountMapper.getAccountByOpenId(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (account == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nick_name");
            String auth = rawDataJson.getString("authenticated");

            Account new_account = new Account();

            new_account.setOpenId(openid);
            new_account.setSkey(skey);
            new_account.setSessionKey(sessionKey);
            new_account.setNickName(nickName);
            new_account.setCreateTime(new Date());
            new_account.setLastVisitTime(new Date());
            new_account.setAuthenticated(Integer.parseInt(auth));

            this.accountMapper.insert(new_account);
        } else {
            // 已存在，更新用户登录时间
            account.setLastVisitTime(new Date());
            // 重新设置会话skey
            account.setSkey(skey);
            this.accountMapper.updateById(account);
        }

        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        GlobalResult result = GlobalResult.build(200, null, skey);
        return result;
    }
}
