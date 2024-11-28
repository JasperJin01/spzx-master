package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;

import java.util.Map;

public interface SysRoleService {
    PageInfo<SysRole> getSysRoleListByPage(Integer page, Integer limit, SysRoleDto sysRoleDto);

    void updateSysRole(SysRole sysRole);

    void addSysRole(SysRole sysRole);

    void deleteSysRoleById(Integer id);

    Map<String, Object> getAllRoleList(Integer userId);
}
