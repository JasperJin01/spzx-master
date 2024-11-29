package com.jasper.manager.controller;

import com.jasper.manager.service.SysRoleMenuService;
import com.jasper.model.dto.system.AssignRoleMenuDto;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @PostMapping("/assignMenuToRole")
    public Result assignMenuToRole(@RequestBody AssignRoleMenuDto assignRoleMenuDto) {
        sysRoleMenuService.assignMenuToRole(assignRoleMenuDto);
        return Result.ok();
    }

}
