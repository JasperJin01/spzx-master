package com.jasper.manager.mapper;

import com.jasper.model.dto.product.CategoryBrandDto;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandMapper {
    List<CategoryBrand> selectByPage(CategoryBrandDto categoryBrandDto);

    void insertCategoryBrand(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    List<Brand> selectBrandByCategoryId(Long categoryId);

    void deleteById(Long id);
}
