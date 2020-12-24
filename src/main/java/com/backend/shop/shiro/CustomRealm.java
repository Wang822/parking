package com.backend.shop.shiro;

import com.backend.shop.service.IAccountService;
import com.backend.shop.util.TokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @ Program       :  com.ljnt.blog.config.CustomRealm
 * @ Description   :  自定义Realm，实现Shiro认证
 * @ Author        :  lj
 * @ CreateDate    :  2020-2-4 18:15
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private IAccountService iAccountService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> role=new HashSet<>();
        role.add("user");
        info.setRoles(role);
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token= (String) authenticationToken.getCredentials();
        // 校验Token
        String accountId = TokenUtil.getAccountId(token);
        if (accountId==null) {
            throw new AuthenticationException("认证失败！");
        }
        int id = Integer.parseInt(accountId);
        if (iAccountService.getById(id) == null) {
            throw new AuthenticationException("No Such Account!");
        }
        return new SimpleAuthenticationInfo(token,token,"MyRealm");
    }
}
