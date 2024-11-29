package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.SysRoleService;
import com.jasper.model.dto.system.SysRoleDto;
import com.jasper.model.entity.system.SysRole;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.ok();
    }

    @PostMapping("/addSysRole")
    public Result addSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.addSysRole(sysRole);
        return Result.ok();
    }

    @DeleteMapping("/deleteSysRoleById/{id}")
    public Result deleteSysRoleById(@PathVariable("id") Integer id) {
        sysRoleService.deleteSysRoleById(id);
        return Result.ok();
    }


    @GetMapping("getAllRoleList/{userId}")
    public Result<Map<String, Object>> getAllRoleList(@PathVariable("userId") Integer userId) {
        Map<String, Object> allRoleList = sysRoleService.getAllRoleList(userId);
        return Result.ok(allRoleList);
    }

    // 根据角色id，返回菜单树 和 角色拥有的菜单ids
    @GetMapping("getSysMenuTreeIds/{roleId}")
    public Result<Map<String, Object>> getSysMenuTreeIds(@PathVariable("roleId") Long roleId) {
        Map<String, Object> sysMenuTreeIds = sysRoleService.getSysMenuTreeIds(roleId);
        return Result.ok(sysMenuTreeIds);
    }

}
