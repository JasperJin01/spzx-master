package com.jasper.model.entity.system;

import com.jasper.model.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// note 如果有 @Builder，就得加上两个构造器的声明。否则可能没办法json序列化。
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
