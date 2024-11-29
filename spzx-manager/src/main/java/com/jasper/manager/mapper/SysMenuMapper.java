package com.jasper.manager.mapper;

import com.jasper.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    List<SysMenu> selectAll();

    void updateById(SysMenu sysMenu);

    void insert(SysMenu sysMenu);

    List<SysMenu> selectByUserId(Long userId);
}
