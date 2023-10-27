package com.easy.service.impl;

import com.easy.common.ResultCode;
import com.easy.domain.dto.UserDTO;
import com.easy.domain.vo.UserVO;
import com.easy.domain.pojo.User;
import com.easy.exception.ServiceException;
import com.easy.mapper.UserMapper;
import com.easy.service.UserService;
import com.easy.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.all();
    }

    @Override
    public List<User> selectList(String username,  String email,  String address) {
        return userMapper.list(username,email,address);
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
    public UserVO login(UserDTO dto) {
        // 获取登录用户
        User user = getUserInfo(dto.getUsername(), dto.getPassword(),null);

        // 未根据用户名和密码查询到用户,说明用户名或密码错误
        if(user == null){
            throw new ServiceException(ResultCode.CODE_600, "用户名或密码错误!");
        }

        // 设置token
        String token = TokenUtils.generateToken(user.getId().toString());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setToken(token);
        return userVO;
    }

    @Override
    public User register(UserDTO userDTO) {
        User user = getUserInfo(userDTO.getUsername(),null,null);
        // 说明用户已存在
        if(user != null){
            throw new ServiceException(ResultCode.CODE_600, "用户名已存在");
        }
        user = new User();
        BeanUtils.copyProperties(userDTO, user);
       // 加密密码存入数据库
        userMapper.create(user);
        return user;
    }

    @Override
    public User getUserInfo(String username,String password,Integer id) {
        User user;
        try{
            List<User> users = userMapper.select(username,password ,id);
            user = users.isEmpty() ? null : users.get(0);
        }catch(Exception exception){
            throw new ServiceException(ResultCode.CODE_500, "系统错误!");
        }
        return user;
    }
}
