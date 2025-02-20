package com.jasper.manager.controller;

import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.ProductSpecService;
import com.jasper.model.entity.product.ProductSpec;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格管理
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService ;

    /**
     * 分页查询商品规格
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page, limit);
        return Result.ok(pageInfo) ;
    }

    /**
     * 保存商品规格
     * @param productSpec
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.ok() ;
    }

    /**
     * 更新商品规格
     * @param productSpec
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.ok() ;
    }

    /**
     * 查询所有商品规格
     * @return
     */
    @GetMapping("findAllProductSpec")
    public Result<List<ProductSpec>> findAllProductSpec() {
        List<ProductSpec> productSpecs = productSpecService.findAllProductSpec();
        return Result.ok(productSpecs) ;
    }
}
