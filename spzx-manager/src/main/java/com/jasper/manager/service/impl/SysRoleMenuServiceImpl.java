package com.jasper.manager.service.impl;

import com.jasper.manager.mapper.SysRoleMenuMapper;
import com.jasper.manager.service.SysRoleMenuService;
import com.jasper.model.dto.system.AssignRoleMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void assignMenuToRole(AssignRoleMenuDto assignRoleMenuDto) {
        // 删除 roleId对应menu
        sysRoleMenuMapper.deleteByRoleId(assignRoleMenuDto.getRoleId());

        // 添加
        sysRoleMenuMapper.insertBatch(assignRoleMenuDto.getRoleId(), assignRoleMenuDto.getMenuIdList());
    }
}
