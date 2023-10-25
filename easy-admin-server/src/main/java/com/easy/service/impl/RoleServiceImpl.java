package com.easy.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easy.domain.pojo.Role;
import com.easy.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author liuxuehan
 * @since 2023-10-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
