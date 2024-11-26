package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.SysRoleService;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysRole")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/getSysRoleListByPage/{page}/{limit}")
    public Result<PageInfo<SysRole>> getSysRoleListByPage(@PathVariable Integer page,
                                                  @PathVariable Integer limit,
                                                          SysRoleDto sysRoleDto) {
        System.out.println("sysRoleDto = " + sysRoleDto);
        PageInfo<SysRole> pageInfo  = sysRoleService.getSysRoleListByPage(page, limit, sysRoleDto);
        return Result.ok(pageInfo);
    }

}
