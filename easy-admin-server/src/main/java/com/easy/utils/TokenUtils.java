package com.easy.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TokenUtils {

    public static String sign;
    @Value("${spring.jwt.sign}")
    public void setSign(String jwtSign){
        sign = jwtSign;
    }

    /**
     * 生成token
     * @param userId
     * */
    public static String generateToken(String userId){
        return JWT.create()
                        .withAudience(userId) //将userId作为载荷
                        .withExpiresAt(DateUtil.offsetHour(new Date(),2)) // 两小时后token过期
                        .sign(Algorithm.HMAC256(sign)); //以password为密钥
    }
}
