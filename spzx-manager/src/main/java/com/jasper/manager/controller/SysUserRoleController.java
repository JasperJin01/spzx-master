package com.jasper.manager.controller;

import com.jasper.manager.service.SysUserRoleService;
import com.jasper.model.dto.system.AssignRoleDto;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping("/admin/system/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    // 为用户分配角色
    @PostMapping("assignRoleToUser")
    public Result assignRoleToUser(@RequestBody AssignRoleDto assignRoleDto) {
        sysUserRoleService.assignRoleToUser(assignRoleDto);
        return Result.ok();
    }
}
