package com.jasper.spzx.product.mapper;

import com.jasper.model.entity.product.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectOneCategories();

    List<Category> selectAllCategories();

    List<Category> selectByParentId(Long parentId);
}
