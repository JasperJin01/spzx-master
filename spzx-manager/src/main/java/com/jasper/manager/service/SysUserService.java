package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.dto.system.SysUserDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser gerUserInfo(String token);

    PageInfo<SysUser> getSysUserListByPage(Integer page, Integer limit, SysUserDto sysUserDto);
}
