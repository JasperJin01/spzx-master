package com.jasper.manager.controller;

import com.jasper.manager.service.SysUserService;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.vo.common.Result;
import com.jasper.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO 跨域

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class IndexController {

    // NOTE @RequestBody
    //  注释总结看一下这篇文章 https://javaguide.cn/system-design/framework/spring/spring-common-annotations.html

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/admin/system/index/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) { // TODO 不添加 @RequestBody，前端发过来的数据不能正确解析
        System.out.println("loginDto = " + loginDto);
        LoginVo loginVo = sysUserService.login(loginDto);

        if (loginVo == null) {
            return Result.fail("账号或密码错误！");
        }
        return Result.ok(loginVo);

    }

}
