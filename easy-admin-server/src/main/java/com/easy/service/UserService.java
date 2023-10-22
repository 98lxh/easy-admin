package com.easy.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.easy.controller.dto.UserDTO;
import com.easy.entity.User;

public interface UserService extends IService<User> {
    // 更新或新增用户
    boolean saveUser(User user);

    User login(UserDTO userDTO);
}
