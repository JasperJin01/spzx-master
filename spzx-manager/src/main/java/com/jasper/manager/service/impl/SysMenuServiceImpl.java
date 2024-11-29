package com.jasper.manager.service.impl;

import com.jasper.manager.mapper.SysMenuMapper;
import com.jasper.manager.service.SysMenuService;
import com.jasper.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> findNodesByTree() {
        List<SysMenu> sysMenus = sysMenuMapper.selectAll();

        List<SysMenu> tree = new ArrayList<>();

        // 构建树形结构
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId() == 0) {
                tree.add(buildChildrens(sysMenus, sysMenu));
            }
        }

        return tree;
    }

    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void addSysMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() == null) sysMenu.setParentId(0L);
        sysMenuMapper.insert(sysMenu);
    }

    private SysMenu buildChildrens(List<SysMenu> sysMenus, SysMenu root) {
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId().equals(root.getId())) {
                root.getChildren().add(buildChildrens(sysMenus, sysMenu));
            }
        }
        return root;
    }
}
