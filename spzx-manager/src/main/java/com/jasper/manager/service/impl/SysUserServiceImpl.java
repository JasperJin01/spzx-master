package com.jasper.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jasper.manager.mapper.SysUserMapper;
import com.jasper.manager.service.SysUserService;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.common.Result;
import com.jasper.model.vo.system.LoginVo;
import com.jasper.util.PowerAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        SysUser sysUser = sysUserMapper.selectUserByName(loginDto.getUserName());

        // 账号不存在的情况
        PowerAssert.notNull(sysUser, "账号不存在！");

        String inputPwd = loginDto.getPassword();
        String md5inputPwd = DigestUtils.md5DigestAsHex(inputPwd.getBytes());

        // 密码错误的情况
        PowerAssert.isTrue(md5inputPwd.equals(sysUser.getPassword()), "密码错误！");


        String token = UUID.randomUUID().toString().replace("-", "");

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 写缓存
        System.out.println("写缓存: user:login:"+token);
        redisTemplate.opsForValue().set("user:login:" + token,
                JSON.toJSONString(sysUser, SerializerFeature.BrowserSecure), 30, TimeUnit.MINUTES);
        // NOTE SerializerFeature.BrowserSecure 指定格式，以浏览器的编码格式编码json字符串，把中文变成 %

        // 返回
        return loginVo;
    }

    @Override
    public SysUser gerUserInfo(String token) {
        // TODO null 空处理
        PowerAssert.notNull(token, "token不能为空！");

        // 从缓存中取出 SysUser
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        System.out.println("sysUser = " + sysUser);
        return sysUser;
    }
}
