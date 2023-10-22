package com.easy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import com.easy.controller.dto.UserDTO;
import com.easy.service.UserService;
import com.easy.common.Result;
import com.easy.utils.StrUtil;
import com.easy.entity.User;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录方法
    @PostMapping("/login")
    public Result login(@RequestBody(required = true) UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
            return Result.error(ResultCode.CODE_400,"用户名或密码不能为空",null);
        }

        User loginUser = userService.login(userDTO);

        Result response = loginUser != null
                ? Result.success("登录成功~",loginUser)
                : Result.error("登录失败,用户名或密码错误~",null);

        return response;
    }

    // 添加用户
    @PostMapping("/create")
    public Result create(@RequestBody User user){
        user.setId(null);

        boolean isCreate = userService.saveUser(user);
        if(isCreate){
            return Result.success("创建用户成功");
        }else{
            return Result.error("创建用户失败");
        }
    }

    // 修改用户
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        boolean isUpdate = userService.saveUser(user);;

        if(isUpdate){
            return Result.success("更新用户成功");
        }else{
            return Result.error("更新用户失败");
        }
    }

    // 查询所有用户
    @GetMapping("/all")
    public Result all(){
        List<User> list = userService.list();
        return Result.success("获取成功",list);
    }

    // 删除用户根据id
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean isDelete = userService.removeById(id);

        if(isDelete){
            return Result.success("删除成功");
        }else{
            return Result.error("删除失败");
        }
    }

    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String username,
                       @RequestParam(defaultValue = "") String email,
                       @RequestParam(defaultValue = "") String address
    ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper  = new QueryWrapper<>();

        // 添加模糊查询条件
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }

        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }

        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }

        IPage<User> userPage = userService.page(page,queryWrapper);
        Map<String,Object> result = new HashMap<>();
        result.put("total",userPage.getTotal());
        result.put("list",userPage.getRecords());
        return Result.success("获取成功",result);
    }
}
