package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.product.CategoryBrandDto;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
