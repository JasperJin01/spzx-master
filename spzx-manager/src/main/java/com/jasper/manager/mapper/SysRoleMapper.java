package com.jasper.manager.mapper;

import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> selectSysRoleListByPage(SysRoleDto sysRoleDto);

    void updateSysRole(SysRole sysRole);

    void insertSysRole(SysRole sysRole);
}
