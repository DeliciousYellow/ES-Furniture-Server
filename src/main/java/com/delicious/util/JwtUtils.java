package com.delicious.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-09 23:17
 **/
public class JwtUtils {

    //设置Token有效时间
    private static long expire = 60*60*24;
//    private static long expire = 1;
    //设置32位密钥(图方便用的微信小程序的密钥)
    private static String secret = "f213fda860e6e72fb1c3d8aa4ed0976e";

    //token生成
    public static String getToken(String AdminCode) {
        //获取当前时间
        Date now = new Date();
        //当前时间加上有效时间，得到过期时间
        Date end = new Date(now.getTime()+expire*1000);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(AdminCode)
                .setIssuedAt(now)
                .setExpiration(end)
                .signWith(SignatureAlgorithm.HS512,secret)//设置签名算法
                .compact();
    }

    //token解析
    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
