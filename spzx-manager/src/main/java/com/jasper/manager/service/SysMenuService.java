package com.jasper.manager.service;

import com.jasper.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodesByTree();

    void updateSysMenu(SysMenu sysMenu);

    void addSysMenu(SysMenu sysMenu);
}
