package com.easy.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.easy.controller.dto.UserDTO;
import com.easy.entity.User;

import java.util.List;

public interface UserService {
    // 查询所有用户
    List<User> selectAll();

    // 根据条件查询用户
    List<User> selectList(
            Integer pageNum,
            Integer pageSize,
            String username,
            String email,
            String address
    );

    // 根据条件查询查询总条数
    Integer selectTotal(
            String username,
            String email,
            String address
    );

    // 更新用户
    Integer updateUser(User user);

    // 删除用户根据id
    Integer deleteUserById(Integer id);

    // 用户登录
    UserDTO login(UserDTO userDTO);

    // 用户注册
    User register(UserDTO userDTO);

    // 获取用户信息
    User getUserInfo(UserDTO userDTO, boolean onlyByUsername);
}
