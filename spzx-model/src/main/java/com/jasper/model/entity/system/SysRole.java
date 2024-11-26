package com.jasper.model.entity.system;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleCode;

    private String description;

}
