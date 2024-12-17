package com.jasper.model.entity.system;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String avatar;
    private String description;
    private Integer status;


}
