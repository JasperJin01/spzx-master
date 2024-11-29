package com.jasper.model.vo.system;

import com.jasper.model.entity.system.SysMenu;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于和前端 Menu 对接
 */
@Data
public class SysMenuVo {

    private String title; // 中文标题
    private String name; // 对应 component（英文）

    // 这个 children 声明的时候不能声明为
    // List<SysMenuVo> children = new ArrayList<>();
    // 否则前端会出问题
    private List<SysMenuVo> children;


    public static List<SysMenuVo> build(List<SysMenu> SysMenuList) {
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();
        for (SysMenu sysMenu : SysMenuList) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(build(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

}
