package com.easy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.annotation.CheckToken;
import com.easy.common.Result;
import com.easy.domain.pojo.Menu;
import com.easy.service.MenuService;
import com.easy.utils.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxuehan
 * @since 2023-10-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    // 新建菜单
    @PostMapping("/create")
    @CheckToken
    public Result create(@RequestBody Menu menu){
        menu.setId(null);
        boolean isCreate = menuService.save(menu);
        return isCreate ? Result.success("创建成功~") : Result.error("创建失败~");
    }

    // 编辑菜单
    @PostMapping("/update")
    @CheckToken
    public Result update(@RequestBody Menu menu){
        Integer id = menu.getId();

        if(id == null){
            return Result.error("请上传菜单的id");
        }

       Boolean isUpdate =  menuService.saveOrUpdate(menu);
        return isUpdate ? Result.success("更新成功~") : Result.error("更新失败~");
    }

    // 根据id获取菜单
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Menu menu = menuService.getById(id);
        return Result.success(menu);
    }

    // 删除角色
    @DeleteMapping("/delete/{id}")
    @CheckToken
    public Result deleteById(@PathVariable Integer id){
        boolean isRemove = menuService.removeById(id);
        return isRemove ? Result.success("删除成功~") : Result.error("删除失败~");
    }

    // 菜单列表
    @GetMapping("/list")
    @CheckToken
    public Result list(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String name
    ){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        if(!StrUtil.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        Map<String,Object> result = new HashMap<>();
        Page<Menu> menuPage = menuService.page(new Page<>(pageNum,pageSize),queryWrapper);

        result.put("total",menuPage.getTotal());
        result.put("list",menuPage.getRecords());
        return Result.success(result);
    }
}
