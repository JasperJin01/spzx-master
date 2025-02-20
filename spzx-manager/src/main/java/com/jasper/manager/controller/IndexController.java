package com.jasper.manager.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name = "系统管理-首页")
@Slf4j
@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) { // TODO 不添加 @RequestBody，前端发过来的数据不能正确解析
        log.info("login::loginDto = " + loginDto);
        LoginVo loginVo = sysUserService.login(loginDto);
        PowerAssert.notNull(loginVo, "用户名或者密码错误");

        return Result.ok(loginVo);
    }

    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo() {
//        SysUser sysUser = sysUserService.getUserInfo(token);
        SysUser sysUser = sysUserService.getUserInfo();

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
