package com.jasper.spzx.product.controller;


import com.jasper.model.entity.product.Category;
import com.jasper.model.vo.common.Result;
import com.jasper.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product/category")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("findCategoryTree")
    public Result<List<Category>> findCategoryTree(){
        List<Category> categoryTree = categoryService.findCategoryTree();
        return Result.ok(categoryTree);
    }

    @GetMapping("findCategoryByParentId/{parentId}")
    public Result<List<Category>> findCategoryByParentId(@PathVariable Long parentId){
        List<Category> categories = categoryService.findCategoryByParentId(parentId);
        return Result.ok(categories);
    }

}
