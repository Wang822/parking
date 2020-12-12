package com.backend.shop.serviece.Impl;

import com.backend.shop.mapper.AccountMapper;
import com.backend.shop.pojo.Account;
import com.backend.shop.serviece.IAccountServiece;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServieceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountServiece {

    @Autowired
    AccountMapper accountMapper;

    public Account getOneByOpenId(String openId) {
        QueryWrapper<Account> query = new QueryWrapper<>();
        query.eq("open_id", openId);
        return accountMapper.selectOne(query);
    }

}
