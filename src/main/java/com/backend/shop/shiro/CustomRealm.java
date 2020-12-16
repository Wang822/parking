package com.backend.shop.shiro;

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

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("CustomRealm 用户授权");
        String username= TokenUtil.getAccountId(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //正确的业务流程是到数据库拿该用户的权限再去进行授权的，这里只是简单的直接授权
//        if (username.equals("admin")){
//            Set<String> role=new HashSet<>();
//            role.add("admin");
//            info.setRoles(role);
//        }else {
//            Set<String> role=new HashSet<>();
//            role.add("user");
//            info.setRoles(role);
//        }
        Set<String> role=new HashSet<>();
        role.add("user");
        info.setRoles(role);
        return info;
    }

    /**
     * 用户身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("CustomRealm 身份认证");
        String token= (String) authenticationToken.getCredentials();
        String accountId = TokenUtil.getAccountId(token);
        //System.out.println(accountId);
        //这里要去数据库查找是否存在该用户，这里直接放行
        if (accountId==null){
            throw new AuthenticationException("认证失败！");
        }
        return new SimpleAuthenticationInfo(token,token,"MyRealm");
    }
}