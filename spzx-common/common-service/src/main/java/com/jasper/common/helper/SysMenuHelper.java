package com.jasper.common.helper;

import com.jasper.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class SysMenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenus) {
        List<SysMenu> tree = new ArrayList<>();

        // 构建树形结构
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId() == 0) {
                tree.add(buildChildrens(sysMenus, sysMenu));
            }
        }
        return tree;
    }

    private static SysMenu buildChildrens(List<SysMenu> sysMenus, SysMenu root) {
        for (SysMenu sysMenu : sysMenus) {
            if (sysMenu.getParentId().equals(root.getId())) {
                root.getChildren().add(buildChildrens(sysMenus, sysMenu));
            }
        }
        return root;
    }

}
