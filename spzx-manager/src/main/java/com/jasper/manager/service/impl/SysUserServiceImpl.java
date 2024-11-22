package com.jasper.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.jasper.manager.mapper.SysUserMapper;
import com.jasper.manager.service.SysUserService;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.common.Result;
import com.jasper.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {
        SysUser sysUser = sysUserMapper.selectUserByName(loginDto.getUserName());

        // 账号不存在的情况
        if (sysUser == null) {
            return null;
        }

        String inputPwd = loginDto.getPassword();
        String md5inputPwd = DigestUtils.md5DigestAsHex(inputPwd.getBytes());

        // 密码错误的情况
        if (!md5inputPwd.equals(sysUser.getPassword())) {
            return null;
        }


        String token = UUID.randomUUID().toString().replace("-", "");

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }
}
