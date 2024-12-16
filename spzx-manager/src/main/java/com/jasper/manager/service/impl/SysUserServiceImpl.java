package com.jasper.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.SysUserMapper;
import com.jasper.manager.service.SysUserService;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.dto.system.SysUserDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.entity.system.SysUserThreadLocal;
import com.jasper.model.vo.system.LoginVo;
import com.jasper.util.PowerAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
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
        // 校验验证码
        String inputCaptcha = loginDto.getCaptcha();
        String inputCodeKey = loginDto.getCodeKey();
        String codeValue = redisTemplate.opsForValue().get("user:login:captcha:" + inputCodeKey);
        System.out.println("codeValue = " + codeValue);
        PowerAssert.isTrue(codeValue != null && inputCaptcha.equals(codeValue), "验证码错误！");


        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());

        // 校验账号
        PowerAssert.notNull(sysUser, "账号不存在！");

        String inputPwd = loginDto.getPassword();
        String md5inputPwd = DigestUtils.md5DigestAsHex(inputPwd.getBytes());

        // 校验密码
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
//    public SysUser gerUserInfo(String token) { // FIXME 这里应该是最早版本，通过controller注释获取token。后续使用登录拦截器，并且把信息存到threadlocal中了。所以感觉这个传参多余
    public SysUser getUserInfo() {
//        PowerAssert.notNull(token, "token不能为空！");

        // 从threadLocal中取出 SysUser
        SysUser sysUser = SysUserThreadLocal.threadLocal.get();
        System.out.println("sysUser = " + sysUser);

        return sysUser;
    }

    @Override
    public PageInfo<SysUser> getSysUserListByPage(Integer page, Integer limit, SysUserDto sysUserDto) {
        PageHelper.startPage(page, limit);
        List<SysUser> sysUsers = sysUserMapper.selectSysUserListByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void addSysUser(SysUser sysUser) {
        // TODO 这里可以添加对添加User信息的校验

        sysUserMapper.insertSysUser(sysUser);
    }

    @Override
    public void deleteSysUserById(Integer id) {
        sysUserMapper.deleteSysUserById(id);
    }


}
