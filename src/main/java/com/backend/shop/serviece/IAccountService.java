package com.backend.shop.serviece;

import com.backend.shop.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

public interface IAccountService extends IService<Account> {
    Account getOneByOpenId(String openId);
    void verify(Account account); //学生认证
}
