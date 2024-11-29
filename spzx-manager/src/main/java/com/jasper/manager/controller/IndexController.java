package com.jasper.manager.controller;

import com.jasper.manager.service.SysMenuService;
import com.jasper.manager.service.SysUserService;
import com.jasper.manager.service.ValidateCodeService;
import com.jasper.model.dto.system.LoginDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.common.Result;
import com.jasper.model.vo.system.LoginVo;
import com.jasper.model.vo.system.SysMenuVo;
import com.jasper.model.vo.system.ValidateCodeVo;
import com.jasper.util.PowerAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO 跨域

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    // NOTE @RequestBody 把前端传过来的数据解析到对象中。
    //  如果是表单的话，没有@RequestBody似乎也能正常运行。
    //  如果是json格式的文件，似乎必须要加上，否则会报415类型错误。
    //  注释总结看一下这篇文章 https://javaguide.cn/system-design/framework/spring/spring-common-annotations.html

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) { // TODO 不添加 @RequestBody，前端发过来的数据不能正确解析
        System.out.println("loginDto = " + loginDto);
        LoginVo loginVo = sysUserService.login(loginDto);
        PowerAssert.notNull(loginVo, "用户名或者密码错误");

        return Result.ok(loginVo);

    }


    // FIXME 前端有问题，传不过来token
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader("token") String token) { // TODO 这个是header传参，所以没有写@。这不怕出错吗？
        SysUser sysUser = sysUserService.gerUserInfo(token);

        return Result.ok(sysUser);
    }

    // 生成验证码
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> getValidateCode() {
        ValidateCodeVo captcha = validateCodeService.getCaptcha();
        return Result.ok(captcha);
    }

    @GetMapping("/menus")
    public Result<List<SysMenuVo>> getMenus() {
        List<SysMenuVo> sysMenuVos = sysMenuService.getMenus();
        return Result.ok(sysMenuVos);
    }

}
