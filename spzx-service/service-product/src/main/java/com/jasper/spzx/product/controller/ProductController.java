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

    /**
     * 分页查询商品列表
     * @param page 当前页码
     * @param limit 每页条数
     * @param productSkuDto 查询条件
     * @return 分页查询结果
     */
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page,
                                                   @PathVariable Integer limit,
                                                   ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.ok(pageInfo) ;
    }

    /**
     * 根据skuId查询商品详情
     * @param skuId skuId
     * @return 商品详情
     */
    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.ok(productItemVo);
    }
}
