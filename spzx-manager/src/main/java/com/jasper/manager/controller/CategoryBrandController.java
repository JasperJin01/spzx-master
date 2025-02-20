package com.jasper.manager.controller;


import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.CategoryBrandService;
import com.jasper.model.dto.product.CategoryBrandDto;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.entity.product.CategoryBrand;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对应表category_brand，分类品牌管理，即对品牌(brand)进行分类(category)
 * 例如: 手机分类下有华为、小米、苹果等品牌
 */
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * 分页查询分类品牌
     * @param page
     * @param limit
     * @param categoryBrandDto
     * @return
     */
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page,
                                                      @PathVariable Integer limit,
                                                      CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.ok(pageInfo);
    }

    /**
     * 新增分类品牌
     * @param categoryBrand
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.ok();
    }

    /**
     * 修改分类品牌
     * @param categoryBrand
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.ok();
    }

    

    /**
     * 根据分类id查询品牌
     * @param categoryId
     * @return
     */
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList = categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.ok(brandList);
    }

    /**
     * 删除分类品牌
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.ok();
    }
}
