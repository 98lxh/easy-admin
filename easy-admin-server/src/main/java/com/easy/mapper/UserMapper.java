package com.easy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper  {
    // 查询所有用户
    List<User> all();

    // 查询列表
    List<User> list(
            @Param("pageNum") Integer pageNum,
            @Param("pageSize") Integer pageSize,
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


    // 根据用户名称和密码查询用户
    List<User> selectUserByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );


    // 创建用户
    void create(User user);
}
