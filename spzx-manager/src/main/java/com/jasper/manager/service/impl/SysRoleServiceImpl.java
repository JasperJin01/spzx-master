package com.jasper.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.SysRoleMapper;
import com.jasper.manager.mapper.SysUserRoleMapper;
import com.jasper.manager.service.SysRoleService;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;
import com.jasper.util.PowerAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public PageInfo<SysRole> getSysRoleListByPage(Integer page, Integer limit, SysRoleDto sysRoleDto) {
        PageHelper.startPage(page, limit);
        List<SysRole> sysRoles = sysRoleMapper.selectSysRoleListByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoles);
        return pageInfo;
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void addSysRole(SysRole sysRole) {
        sysRoleMapper.insertSysRole(sysRole);
    }

    @Override
    public void deleteSysRoleById(Integer id) {
        // 只有不被分配的角色才可以删除
        List<Integer> userIds = sysUserRoleMapper.selectUserIdByRoleId(id);

        PowerAssert.isTrue(userIds.size() == 0, "该角色已被分配，无法删除");

        sysRoleMapper.deleteSysRoleById(id);
    }

    @Override
    public Map<String, Object> getAllRoleList(Integer userId) {
        // 查询所有角色
        List<SysRole> sysRoles = sysRoleMapper.selectSysRoleList();

        // 查询用户id拥有的角色id
        List<Integer> roleIds = sysUserRoleMapper.selectRoleIdsByUserId(userId);


        Map<String, Object> m = new HashMap<>();
        m.put("allRoles", sysRoles);
        m.put("userRoleIds", roleIds);

        return m;
    }


}
