package com.jasper.spzx.product.service.impl;

import com.jasper.model.entity.product.Category;
import com.jasper.spzx.product.mapper.CategoryMapper;
import com.jasper.spzx.product.service.CategoryService;
import com.jasper.spzx.product.utils.CategoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Category> findOneCategory() {
        List<Category> firstCategories = categoryMapper.selectOneCategories();
        return firstCategories;
    }

    @Cacheable(value = "findCategoryTree", key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        // 查询缓存
        // 缓存有返回结果
        // 缓存没有查询数据库
        // 数据库有返回结果，并且存入缓存
        List<Category> categories = categoryMapper.selectAllCategories();
        return CategoryHelper.buildTree(categories);
    }

    @Cacheable(value = "findCategoryByParentId",key = "#parentId")
    @Override
    public List<Category> findCategoryByParentId(Long parentId) {
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        return categories;
    }


}
