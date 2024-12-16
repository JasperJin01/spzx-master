package com.jasper.manager.controller;


import com.github.pagehelper.PageInfo;
import com.jasper.manager.service.ProductService;
import com.jasper.model.dto.product.ProductDto;
import com.jasper.model.entity.product.Product;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/admin/product/product")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.ok(product);
    }

    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);
        return Result.ok(pageInfo);
    }

    @PostMapping("save")
    public Result saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return Result.ok(null);
    }

    // 修改商品的上下架
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.ok();
    }

    // 修改商品的审批
    @GetMapping("updateAuditStatus/{id}/{status}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateAuditStatus(id, status);
        return Result.ok();
    }


}
