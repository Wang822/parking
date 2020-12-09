package com.backend.parking.service.impl;

import com.backend.parking.entity.Account;
import com.backend.parking.mapper.AccountMapper;
import com.backend.parking.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dm
 * @since 2020-12-09
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account findByOpenId(String openId) {
        return new Account();
    }
}
