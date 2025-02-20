package com.jasper.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.entity.system.SysUserThreadLocal;
import com.jasper.util.PowerAssert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 登录认证拦截器
 * 
 * 拦截<b>所有请求</b>，检查请求头中的token是否有效
 * 前端请求需要携带token
 * 如果有效，则将用户信息保存到ThreadLocal中
 *
 * note: HandlerInterceptor 是 Spring MVC 提供的接口，允许在处理请求之前/之后/请求完成时插入自定义逻辑，相当于面向切面编程
 * 使用方法: 自定义拦截器(LoginAuthInterceptor)->注册拦截器:在 WebMvcConfigurer 中配置或使用xml(略)
 */
@Slf4j
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    //
    //  preHandle

    // FIXME 这个咋用的？以后所有的请求都要被这个拦截，然后访问一下redis吗？
    // 前端请求携带token
    // prehandle
    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginAuthInterceptor 认证拦截，请求url: " + request.getRequestURL());

        // 获取请求方式
        String method = request.getMethod();
        // 如果是跨域预检请求，直接放行
        if("OPTIONS".equals(method)) {
            return true ;
        }

        // 获取token
        String token = request.getHeader("token");
        // FIXME 断言逻辑别搞混了，把true和false搞反了
        PowerAssert.isTrue(!StringUtil.isEmpty(token) ,"token不能为空");

        // 查询缓存
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login:" + token);

        PowerAssert.isTrue(!StringUtil.isEmpty(sysUserInfoJson), "不存在用户token缓存");

        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        SysUserThreadLocal.threadLocal.set(sysUser); // 将用户信息保存到ThreadLocal中


        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire("user:login:" + token , 70 , TimeUnit.MINUTES) ;

        // 放行
        return true;
    }
}
