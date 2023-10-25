package com.easy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.annotation.CheckToken;
import com.easy.common.Result;
import com.easy.domain.pojo.Role;
import com.easy.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxuehan
 * @since 2023-10-25
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    // 新建角色
    @PostMapping("/create")
    @CheckToken
    public Result create(@RequestBody Role role){
        role.setId(null);
        roleService.saveOrUpdate(role);
        return Result.success("新建角色成功~");
    }

    // 更新角色
    @PostMapping("/update")
    @CheckToken
    public Result update(@RequestBody Role role){
       Integer id = role.getId();
       if(id == null){
           return Result.error("请上传修改角色的id");
       }
       roleService.saveOrUpdate(role);
       return Result.success("角色修改成功");

    }

    @GetMapping("/{id}")
    @CheckToken
    public Result geById(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return Result.success(role);
    }

    // 角色列表
    @GetMapping("/list")
    @CheckToken
    public Result list(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        Map<String,Object> result = new HashMap<>();

        Page<Role> rolePage = roleService.page(new Page<>(pageNum,pageSize),queryWrapper);

        result.put("total",rolePage.getTotal());
        result.put("list",rolePage.getRecords());

        return Result.success(result);
    }
}
