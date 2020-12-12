package com.backend.shop.serviece;

import com.backend.shop.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IAccountServiece extends IService<Account> {
    Account getOneByOpenId(String openId);
}
