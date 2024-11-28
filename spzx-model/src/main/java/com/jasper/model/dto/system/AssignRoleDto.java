package com.jasper.model.dto.system;

import lombok.Data;

import java.util.List;

@Data
public class AssignRoleDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private List<Long> roleIds;
}
