package com.jasper.manager.service.impl;

import com.jasper.common.helper.SysMenuHelper;
import com.jasper.manager.mapper.SysMenuMapper;
import com.jasper.manager.service.SysMenuService;
import com.jasper.model.entity.system.SysMenu;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.entity.system.SysUserThreadLocal;
import com.jasper.model.vo.system.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> findNodesByTree() {
        return SysMenuHelper.buildTree(sysMenuMapper.selectAll());
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

    @Override
    public List<SysMenuVo> getMenus() {
        // 获取当前用户id
        SysUser sysUser = SysUserThreadLocal.threadLocal.get();

        // 获取用户角色，根据角色获取菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectByUserId(sysUser.getId());
        // 构建树形结构
        List<SysMenu> sysMenuTree = SysMenuHelper.buildTree(sysMenus);

        return SysMenuVo.build(sysMenuTree);
    }


}
