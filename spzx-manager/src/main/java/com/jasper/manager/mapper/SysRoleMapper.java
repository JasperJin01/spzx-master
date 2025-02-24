package com.jasper.manager.mapper;

import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;

import java.util.List;

public interface SysRoleMapper {
    List<SysRole> selectSysRoleListByPage(SysRoleDto sysRoleDto);

    void updateSysRole(SysRole sysRole);

    void insertSysRole(SysRole sysRole);

    void deleteSysRoleById(Integer id);

    // 查询所有角色，其实是可以和 selectSysRoleListByPage 方法合并的
    List<SysRole> selectSysRoleList();

    // sql 添加 is_half = 0，为了防止前端回显数据时，el-tree setCheckedKeys 根节点会把子节点都选上
    List<Long> selectMenuIdsByRoleId(Long roleId);
}
