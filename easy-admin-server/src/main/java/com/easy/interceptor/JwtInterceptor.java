package com.easy.interceptor;

import cn.hutool.jwt.JWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.easy.annotation.CheckToken;
import com.easy.common.ResultCode;
import com.easy.domain.pojo.User;
import com.easy.exception.ServiceException;
import com.easy.service.UserService;
import com.easy.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 token
        String token = request.getHeader("token");

        // 如果映射到的不是方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CheckToken checkToken =  handlerMethod.getMethodAnnotation(CheckToken.class);

        // 如果没有CheckToken注解不校验权限，直接通过
        if(checkToken == null){
            return true;
        }

        // 未上传token
        if(token == null){
            throw new ServiceException(ResultCode.CODE_401,  "未上传token,请重新登录");
        }

        // 获取userId
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch(JWTException jwtException){
            throw new ServiceException(ResultCode.CODE_401, "token验证失败,请重新登录");
        }

        // 用户不存在
        User user = userService.getUserInfo(null,null, Integer.valueOf(userId));
        if(user == null){
            throw new ServiceException(ResultCode.CODE_401, "用户不存在,请重新登录");
        }

        // 验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenUtils.sign)).build();
        try{
            jwtVerifier.verify(token);
        }catch(JWTVerificationException jwtVerificationException){
            throw new ServiceException(ResultCode.CODE_401,"token验证失败,请重新登录");
        }

        return true;
    }
}
