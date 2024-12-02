package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category extends BaseEntity {
    private String name;
    private String imageUrl;
    private Long parentId;
    private Integer status;
    private Integer orderNum;
    private Boolean hasChildren = true;
    private List<Category> children = new ArrayList<>();
}
