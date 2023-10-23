package com.easy.service.impl;

import com.easy.common.ResultCode;
import com.easy.controller.dto.UserDTO;
import com.easy.entity.User;
import com.easy.exception.ServiceException;
import com.easy.mapper.UserMapper;
import com.easy.service.UserService;
import com.easy.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
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
    public UserDTO login(UserDTO userDTO) {
        User loginUser = getUserInfo(userDTO,false);

        // 设置token
        String token = TokenUtils.generateToken(loginUser.getId().toString(),loginUser.getPassword());

        if(loginUser != null){
            BeanUtils.copyProperties(loginUser, userDTO);
            userDTO.setToken(token);
            return userDTO;
        } else {
            System.out.println(loginUser);
            throw new ServiceException(ResultCode.CODE_600, "用户名或密码错误!");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User user = getUserInfo(userDTO,true);

        if(user == null){
            user = new User();
            BeanUtils.copyProperties(userDTO,user);
            userMapper.create(user);
        }else{
            throw new ServiceException(ResultCode.CODE_600,"用户已存在~");
        }

        return user;
    }

    @Override
    public User getUserInfo(UserDTO userDTO, boolean onlyByUsername) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        User loginUser;

        try{
            List<User> users = userMapper.selectUserByUsernameAndPassword(
                    username,
                    onlyByUsername ? null : password
            );

            loginUser = users.isEmpty() ? null : users.get(0);
        }catch(Exception exception){
            throw new ServiceException(ResultCode.CODE_500,"系统错误!");
        }

        return loginUser;
    }
}
