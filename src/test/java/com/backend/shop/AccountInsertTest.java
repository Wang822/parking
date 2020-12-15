package com.backend.shop;

import com.backend.shop.mapper.AccountMapper;
import com.backend.shop.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountInsertTest {

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void test() {
        Account account = new Account();
        account.setOpenId("openid");
//        account.setSkey("skey");
        account.setCreateTime(new Date());
        account.setLastVisitTime(new Date());
//        account.setSessionKey("sessionKey");
        account.setNickName("TEST Account");
        account.setAuthenticated(false);

        accountMapper.insert(account);
        System.out.println("accountId" + account.getAccountId());
    }
}
