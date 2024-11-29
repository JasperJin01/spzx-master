package com.jasper.manager.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.entity.system.SysUserThreadLocal;
import com.jasper.util.PowerAssert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    // FIXME 这个咋用的？以后所有的请求都要被这个拦截，然后访问一下redis吗？
    // 前端请求携带token
    // login函数存储token到threadlocal
    // prehandle
    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginAuthInterceptor 去 redis 缓存查看token，并且把SysUser保存到ThreadLocal");

        // 获取请求方式
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {      // 如果是跨域预检请求，直接放行
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
        SysUserThreadLocal.threadLocal.set(sysUser);


        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire("user:login:" + token , 30 , TimeUnit.MINUTES) ;

        // 放行
        return true;
    }
}
