package com.easy.controller;

import com.easy.common.ResultCode;
import com.easy.controller.dto.UserDTO;
import com.easy.utils.StrUtil;
import com.easy.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.easy.service.UserService;
import com.easy.common.Result;
import com.easy.entity.User;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块") // 用在类上描述整个Controller接口信息
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有用户") // 描述方法的基本信息
    @GetMapping("/all")
    public Result all(){
        List<User> allUsers = userService.selectAll();
        return Result.success("获取成功",  allUsers);
    }

    @ApiOperation(value = "根据条件查询用户")
    @GetMapping("/list")
    public Result list(
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer  pageSize,
                       @RequestParam(defaultValue = "") String username,
                       @RequestParam(defaultValue = "") String email,
                       @RequestParam(defaultValue = "") String address
    ){
        pageNum = pageNum - 1;
        Integer total = userService.selectTotal(username,email,address);
        List<User> list = userService.selectList(pageNum,pageSize,username,email,address);
        Map<String,Object> result = new HashMap<>();
        result.put("list",list);
        result.put("total",total);

        return Result.success("获取成功!",result);
    }

    // 修改用户
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    public Result update(@RequestBody(required = false) User user){
        if (user == null ||user.getId() == null){
            return Result.error("请上传修改用户的id~");
        }
        Integer updates = userService.updateUser(user);;
        return updates == 0 ? Result.error("用户修改失败~") : Result.success("用户修改成功~");
    }

    // 删除用户根据id
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        Integer deletes = userService.deleteUserById(id);
        return deletes == 0 ? Result.error("删除用户失败~") : Result.success("删除用户成功~");
    }

    // 用户登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody(required = true) UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
            return Result.error(ResultCode.CODE_400,"用户名或密码不能为空",null);
        }

        UserDTO dto = userService.login(userDTO);
        return Result.success("登录成功~",dto);
    }

    // 用户注册
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
            return Result.error(ResultCode.CODE_400,"用户名或密码不能为空",null);
        }

        User user = userService.register(userDTO);
        return Result.success(user);
    }
}
