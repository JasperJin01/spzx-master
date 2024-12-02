package com.jasper.manager.mapper;

import com.jasper.model.entity.product.Category;
import com.jasper.model.vo.product.CategoryExcelVo;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectByParentId(Long id);

    Long selectCountByParentId(Long id);

    void insertBath(List<CategoryExcelVo> list);

    List<Category> selectAll();
}

