package com.jasper.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.model.dto.system.SysUserDto;
import com.jasper.model.entity.system.SysUser;

import java.util.List;

// 这里引入了BaseMapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByUserName(String userName);

    List<SysUser> selectSysUserListByPage(SysUserDto sysUserDto);

    void updateSysUser(SysUser sysUser);

    void insertSysUser(SysUser sysUser);

    void deleteSysUserById(Integer id);


}
