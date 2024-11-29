package com.jasper.model.dto.system;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AssignRoleMenuDto {
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * Map: 菜单id、isHalf (0:全选 1:半选)
     * e.g. [{"id":2,"isHalf":0}, {"id":1, "isHalf":1}]
     */
    private List<Map<String, Number>> menuIdList;

}
