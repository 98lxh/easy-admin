package com.easy.domain.vo;


import lombok.Data;

/**
 *  用户登录响应给前端的数据
 * */
@Data
public class UserVO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String email;
    private String token;
}
