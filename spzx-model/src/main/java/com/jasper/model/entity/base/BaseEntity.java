package com.jasper.model.entity.base;

import lombok.Data;

import java.util.Date;

// TODO 这里面的date引入的正确吗？和sql数据库中的类型可以直接转换过来吗？
@Data
public class BaseEntity {
    private Long id;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;
}
