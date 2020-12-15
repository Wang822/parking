package com.backend.shop.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 配置token实体bean进行拓展，使其适应shiro框架
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
