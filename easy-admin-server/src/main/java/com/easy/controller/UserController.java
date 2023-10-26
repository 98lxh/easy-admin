package com.easy.controller;

import com.easy.annotation.CheckToken;
import com.easy.common.ResultCode;
import com.easy.domain.dto.UserDTO;
import com.easy.domain.vo.UserVO;
import com.easy.utils.StrUtil;
import com.easy.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.easy.service.UserService;
import com.easy.common.Result;
import com.easy.domain.pojo.User;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块") // 用在类上描述整个Controller接口信息
public class UserController {
    @Resource
    private UserService userService;

    // 根据token获取用户信息
    @ApiModelProperty(value = "获取用户信息")
    @GetMapping("/info")
    @CheckToken
    public Result info(){
        User user = TokenUtils.getCurrentUser();
        return user == null
                ? Result.error("获取用户信息失败,请检查是否token")
                : Result.success(user);
    }

    @ApiOperation(value = "查询所有用户") // 描述方法的基本信息
    @GetMapping("/all")
    @CheckToken
    public Result all(){
        List<User> allUsers = userService.selectAll();
        return Result.success("获取成功",  allUsers);
    }

    @ApiOperation(value = "根据条件查询用户")
    @GetMapping("/list")
    @CheckToken
    public Result list(
      @RequestParam(defaultValue = "1") Integer pageNum,
      @RequestParam(defaultValue = "5") Integer  pageSize,
      @RequestParam(defaultValue = "") String username,
      @RequestParam(defaultValue = "") String email,
      @RequestParam(defaultValue = "") String address
    ){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userService.selectList(username,email,address);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        long total =  pageInfo.getTotal();

        Map<String,Object> result = new HashMap<>();
        result.put("list",list);
        result.put("total",total);

        return Result.success("获取成功!",result);
    }

    // 修改用户
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    @CheckToken
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
    @CheckToken
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

        UserVO userVO = userService.login(userDTO);
        return Result.success("登录成功~",userVO);
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
