package com.jasper.manager.service;

import com.jasper.model.entity.system.SysMenu;
import com.jasper.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findNodesByTree();

    void updateSysMenu(SysMenu sysMenu);

    void addSysMenu(SysMenu sysMenu);

    List<SysMenuVo> getMenus();
}
