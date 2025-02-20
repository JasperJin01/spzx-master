package com.jasper.manager.controller;

import com.jasper.manager.service.SysMenuService;
import com.jasper.model.entity.system.SysMenu;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysMenu")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单节点
     * @return
     */
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findNodesByTree();
        return Result.ok(list);
    }

    /**
     * 更新菜单
     * @param sysMenu
     * @return
     */
    @PutMapping("/updateSysMenu")
    public Result updateSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return Result.ok();

    }

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @PostMapping("/addSysMenu")
    public Result addSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addSysMenu(sysMenu);
        return Result.ok();
    }

}
