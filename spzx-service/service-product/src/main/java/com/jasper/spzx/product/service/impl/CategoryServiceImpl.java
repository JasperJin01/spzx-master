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

    // fixme 这些都是在查什么啊？

    /**
     * 查询一级分类
     * @return 一级分类列表
     */
    @Override
    public List<Category> findOneCategory() {
        List<Category> firstCategories = categoryMapper.selectOneCategories();
        return firstCategories;
    }

    /**
     * 查询分类树（全部分类）
     * @return 分类树
     */
    @Cacheable(value = "findCategoryTree", key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        // 查询缓存
        // 缓存有 返回结果
        // 缓存没有 查询数据库 返回结果，并且存入缓存
        List<Category> categories = categoryMapper.selectAllCategories();
        return CategoryHelper.buildTree(categories);
    }

    /**
     * 根据 parentID 查询分类
     * @param parentId
     * @return 分类树
     */
    @Cacheable(value = "findCategoryByParentId",key = "#parentId")
    @Override
    public List<Category> findCategoryByParentId(Long parentId) {
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        return categories;
    }


}
