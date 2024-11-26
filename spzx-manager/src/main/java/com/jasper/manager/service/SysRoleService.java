package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;

public interface SysRoleService {
    PageInfo<SysRole> getSysRoleListByPage(Integer page, Integer limit, SysRoleDto sysRoleDto);
}
