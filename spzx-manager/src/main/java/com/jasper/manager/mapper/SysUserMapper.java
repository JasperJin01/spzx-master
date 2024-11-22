package com.jasper.manager.mapper;

import com.jasper.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

// FIXME 这个mapper用不用加@Mapper注释？

public interface SysUserMapper {

    SysUser selectUserByName(String userName);
}
