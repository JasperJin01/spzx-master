package com.jasper.spzx.product.service;

import com.jasper.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();

    List<Category> findCategoryByParentId(Long parentId);

}
