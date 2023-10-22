package com.easy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    // 查询所有用户
    @Select("SELECT * FROM sys_user")
    List<User> findAll();

    // 创建用户
    @Insert("INSERT INTO " +
            "sys_user(username,password,nickname,email,phone,address) " +
            "VALUES(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int create(User user);

    // 更新用户
    int update(User user);

    // 删除用户根据id
    @Delete("DELETE  FROM sys_user WHERE id = #{id}")
    // 删除用户通过id
    int deleteById(@Param("id") Integer id);

    // 查询列表
    @Select("SELECT * FROM sys_user WHERE username LIKE CONCAT('%',#{username},'%') LIMIT #{pageNum}, #{pageSize}")
    List<User> list(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("username") String username);

    @Select("SELECT COUNT(*) FROM sys_user")
    Integer selectTotal();
}
