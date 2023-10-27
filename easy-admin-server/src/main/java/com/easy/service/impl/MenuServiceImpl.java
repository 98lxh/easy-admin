package com.easy.service.impl;

import com.easy.domain.pojo.Menu;
import com.easy.mapper.MenuMapper;
import com.easy.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liuxuehan
 * @since 2023-10-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
