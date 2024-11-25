package com.jasper.manager.mapper;

import com.jasper.model.entity.system.SysUser;

// 这个mapper用不用加@Mapper注释？
// 如果Application添加@MapperScan了就不用添加@Mapper

public interface SysUserMapper {

    SysUser selectByUserName(String userName);
}
