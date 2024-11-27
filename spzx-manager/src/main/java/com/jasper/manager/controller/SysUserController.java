package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.SysUserService;
import com.jasper.model.dto.system.SysUserDto;
import com.jasper.model.entity.system.SysUser;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("getSysUserListByPage/{page}/{limit}")
    public Result<PageInfo<SysUser>> getSysUserListByPage(@PathVariable("page") Integer page,
                                                          @PathVariable("limit") Integer limit,
                                                          SysUserDto sysUserDto) {
        System.out.println("sysUserDto = " + sysUserDto);
        PageInfo<SysUser> sysUsers = sysUserService.getSysUserListByPage(page, limit, sysUserDto);
        return Result.ok(sysUsers);
    }


}