package com.easy.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.easy.common.Result;
import com.easy.common.ResultCode;
import com.easy.domain.pojo.User;
import com.easy.exception.ServiceException;
import com.easy.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
public class TokenUtils {

    public static String sign;

    @Resource
    private UserService userService;
    private static UserService staticUserService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

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

    public static User getCurrentUser(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");

        // token为空返回null
        if(StrUtil.isEmpty(token)){
            return null;
        }

        try{
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getUserInfo(null,null,Integer.valueOf(userId));
        }catch (Exception e){
            return null;
        }
    }
}
