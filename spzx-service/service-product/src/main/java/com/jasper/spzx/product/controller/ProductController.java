package com.jasper.spzx.product.controller;


import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.product.ProductItemVo;
import com.jasper.model.dto.product.ProductSkuDto;
import com.jasper.model.entity.product.ProductSku;
import com.jasper.model.vo.common.Result;
import com.jasper.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page,
                                                   @PathVariable Integer limit,
                                                   ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.ok(pageInfo) ;
    }


    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.ok(productItemVo);
    }
}
