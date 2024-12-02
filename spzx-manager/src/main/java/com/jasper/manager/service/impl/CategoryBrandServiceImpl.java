package com.jasper.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.CategoryBrandMapper;
import com.jasper.manager.service.CategoryBrandService;
import com.jasper.model.dto.product.CategoryBrandDto;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page, limit);
        List<CategoryBrand> categoryBrands = categoryBrandMapper.selectByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(categoryBrands);
        return pageInfo;
    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.insertCategoryBrand(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {

        List<Brand> brands = categoryBrandMapper.selectBrandByCategoryId(categoryId);

        return brands;
    }
}
