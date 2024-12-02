package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.BrandService;
import com.jasper.model.entity.product.Brand;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/product/brand")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.ok(pageInfo);
    }

    @PostMapping("save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.ok();
    }

    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.ok();
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.ok();
    }


}
