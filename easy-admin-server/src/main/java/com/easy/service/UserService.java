package com.easy.service;
import com.easy.domain.dto.UserDTO;
import com.easy.domain.vo.UserVO;
import com.easy.domain.pojo.User;

import java.util.List;

public interface UserService {
    // 查询所有用户
    List<User> selectAll();

    // 根据条件查询用户
    List<User> selectList( String username,  String email,  String address);

    // 更新用户
    Integer updateUser(User user);

    // 删除用户根据id
    Integer deleteUserById(Integer id);

    // 用户登录
    UserVO login(UserDTO userDTO);

    // 用户注册
    User register(UserDTO userDTO);

    // 获取用户信息
    User getUserInfo(String username,String password,Integer id);
}
