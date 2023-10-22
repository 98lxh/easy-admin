package com.easy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.controller.dto.UserDTO;
import com.easy.entity.User;
import com.easy.mapper.UserMapper;
import com.easy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveUser(User user) {
        if(user.getId() == null){
            return save(user); // mybatis-plus 提供的的方法表示插入数据
        }else{
            return updateById(user);
        }
    }

    @Override
    public User login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        List<User> list = list(queryWrapper);
        return list == null || list.isEmpty() ? null : list.get(0);
    }
}
