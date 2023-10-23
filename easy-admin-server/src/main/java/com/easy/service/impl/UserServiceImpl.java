package com.easy.service.impl;

import com.easy.controller.dto.UserDTO;
import com.easy.entity.User;
import com.easy.mapper.UserMapper;
import com.easy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.all();
    }

    @Override
    public List<User> selectList(
                Integer pageNum,
                Integer pageSize,
                String username,
                String email,
                String address
    ) {
        return userMapper.list(pageNum,pageSize,username,email,address);
    }

    @Override
    public Integer selectTotal(
            String username,
            String email,
            String address
    ) {
        return userMapper.total(username,email,address);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User login(UserDTO userDTO) {
        List<User> users = userMapper.selectUserByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());
        return users.size() == 0 || users == null ? null : users.get(0);
    }
}
