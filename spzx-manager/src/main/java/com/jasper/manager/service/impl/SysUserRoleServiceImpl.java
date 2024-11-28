package com.jasper.manager.service.impl;

import com.jasper.manager.mapper.SysUserRoleMapper;
import com.jasper.manager.service.SysUserRoleService;
import com.jasper.model.dto.system.AssignRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    // TODO 感觉这种操作应该需要事务
    @Override
    public void assignRoleToUser(AssignRoleDto assignRoleDto) {
        // 删除
        sysUserRoleMapper.deleteByUserId(assignRoleDto.getUserId());

        // 添加
        sysUserRoleMapper.insertBatch(assignRoleDto.getUserId(), assignRoleDto.getRoleIds());


    }
}
