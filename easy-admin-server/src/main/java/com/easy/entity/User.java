package com.easy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User {
    @TableId(type = IdType.AUTO) // 声明为主键
    private Integer id;
    private String username;
    @JsonIgnore // 忽略某个字段不展示
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private String address;
}
