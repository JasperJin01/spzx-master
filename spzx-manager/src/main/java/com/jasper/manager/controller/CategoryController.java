package com.jasper.manager.controller;

import com.jasper.manager.service.CategoryService;
import com.jasper.model.vo.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jasper.model.entity.product.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 分类管理
 * 总共有三级分类。例如:
 * 数码办公-手机通讯-手机
 * 数码办公-手机通讯-对讲机
 * 数码办公-摄影摄像-数码相机
 * 数码办公-摄影摄像-镜头
 * 家用电器-大家电-空调
 * 等等
 * 
 */
@RestController
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RequestMapping(value = "/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父id查询分类
     * @param id
     * @return
     */
    @GetMapping("/findByParentId/{id}")
    public Result<List<Category>> findByParentId(@PathVariable("id") Long id) {
        List<Category> categories = categoryService.findByParentId(id);
        return Result.ok(categories);
    }

    /**
     * 通过文件导入分类
     * @param multipartFile
     * @return
     */
    @PostMapping("importData")
    public Result importData(@RequestParam("file") MultipartFile multipartFile) {
        categoryService.importData(multipartFile);
        return Result.ok();
    }

    /**
     * 导出分类为表格
     * @param response
     */
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }


}
