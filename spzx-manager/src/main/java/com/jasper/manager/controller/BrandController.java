package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.BrandService;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对品牌进行增删改查
 */
@RestController
@RequestMapping(value = "/admin/product/brand")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 分页查询品牌
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.ok(pageInfo);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.ok();
    }

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.ok();
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.ok();
    }

    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping("findAll")
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();
        return Result.ok(brands) ;
    }


}
