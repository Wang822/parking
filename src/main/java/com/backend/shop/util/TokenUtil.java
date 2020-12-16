package com.backend.shop.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * token工具类（生成、验证）
 */
public class TokenUtil {
    //这里的token属性配置最好写在配置文件中，这里为了方面直接写成静态属性
    public static final long EXPIRE_TIME= 4*60*60*1000;//token到期时间4小时，毫秒为单位
    public static final long REFRESH_EXPIRE_TIME=30*60;//RefreshToken到期时间为30分钟，秒为单位
    private static final String TOKEN_SECRET="ljdyaishijin**3nkjnj??";  //密钥盐

    /**
     * 生成token
     */
    public static String sign(String accountId, Long currentTime){

        String token = null;
        try {
            Date expireAt=new Date(currentTime+EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")//发行人
                    .withClaim("accountId", accountId)//存放数据
                    .withClaim("currentTime", currentTime)
                    .withExpiresAt(expireAt)//过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException je) {
            je.printStackTrace();
        }
        return token;
    }

    // jwtUtils.getClaimByToken
    // jwtUtils.isTokenExpired
    /**
     * token验证
     */
    public static boolean verify(String token) throws Exception {

        JWTVerifier jwtVerifier= JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();//创建token验证器
        DecodedJWT decodedJWT = jwtVerifier.verify(token); //若不成功, exception
        System.out.println("*TokenUtil.verify认证");
        System.out.println("Account ID:   " + decodedJWT.getClaim("accountId").asString());
        System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
        return true;
    }


    public static String getAccountId(String token){
        try{
            DecodedJWT decodedJWT=JWT.decode(token);
            return decodedJWT.getClaim("accountId").asString();

        } catch (JWTCreationException e){
            return null;
        }
    }
    public static Long getCurrentTime(String token){
        try{
            DecodedJWT decodedJWT=JWT.decode(token);
            return decodedJWT.getClaim("currentTime").asLong();

        }catch (JWTCreationException e){
            return null;
        }
    }

    /**
     * 其它API调用
     * 得到已验证的token对应的AccountId/UserId
     */
    public static Integer getUserId(String token) {
        DecodedJWT decodedJWT=JWT.decode(token);
        String str = decodedJWT.getClaim("accountId").asString();
        return Integer.valueOf(str);
    }



}

