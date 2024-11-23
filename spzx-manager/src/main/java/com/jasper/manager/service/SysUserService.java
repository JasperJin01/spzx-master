package com.jasper.manager.service;

import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.system.LoginVo;
import org.springframework.stereotype.Service;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser gerUserInfo(String token);
}
