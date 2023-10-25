package com.easy.mapper;

import com.easy.domain.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper  {
    // 查询所有用户
    List<User> all();

    // 查询列表
    List<User> list(
            @Param("username") String username,
            @Param("email") String email,
            @Param("address") String address
    );

    // 查询根据条件查询总条数
    Integer total(
            @Param("username") String username,
            @Param("email") String email,
            @Param("address") String address
    );

    // 更新用户
    int update(User user);

    // 删除用户根据id
    int deleteById(@Param("id") Integer id);


    // 根据用户名称或密码或id查询用户
    List<User> select(
            @Param("username") String username,
            @Param("password") String password,
            @Param("id") Integer id
    );

    // 创建用户
    void create(User user);
}
