package com.jasper.manager.controller;

import com.jasper.manager.service.CategoryService;
import com.jasper.model.vo.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jasper.model.entity.product.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping(value = "/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/findByParentId/{id}")
    public Result<List<Category>> findByParentId(@PathVariable("id") Long id) {
        List<Category> categories = categoryService.findByParentId(id);
        return Result.ok(categories);
    }

    @PostMapping("importData")
    public Result importData(@RequestParam("file") MultipartFile multipartFile) {
        categoryService.importData(multipartFile);
        return Result.ok();
    }

    // 没有返回结果！
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }


}
