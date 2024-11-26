package com.jasper.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.SysRoleMapper;
import com.jasper.manager.service.SysRoleService;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> getSysRoleListByPage(Integer page, Integer limit, SysRoleDto sysRoleDto) {
        PageHelper.startPage(page, limit);
        List<SysRole> sysRoles = sysRoleMapper.selectSysRoleListByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }
}
