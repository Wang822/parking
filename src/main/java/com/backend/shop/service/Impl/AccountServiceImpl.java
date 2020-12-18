package com.backend.shop.service.Impl;

import com.backend.shop.mapper.AccountMapper;
import com.backend.shop.pojo.Account;
import com.backend.shop.service.IAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getOneByOpenId(String openId) {
        QueryWrapper<Account> query = new QueryWrapper<>();
        query.eq("open_id", openId);
        return accountMapper.selectOne(query);
    }

    public void verify(Account account) {
        UpdateWrapper<Account> update = new UpdateWrapper<>();
        update.eq("account_id", account.getAccountId()).set("authenticated", true);
        accountMapper.update(account, update);
    }

}
