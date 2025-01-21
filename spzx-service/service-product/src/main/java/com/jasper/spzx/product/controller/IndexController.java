package com.jasper.spzx.product.controller;


import com.jasper.model.entity.product.Category;
import com.jasper.model.entity.product.ProductSku;
import com.jasper.model.vo.common.Result;
import com.jasper.model.vo.product.IndexVo;
import com.jasper.spzx.product.service.CategoryService;
import com.jasper.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping("index")
    public Result<IndexVo> index() {
        List<Category> categoryList = categoryService.findOneCategory();// 一级分类
        List<ProductSku> productSkus = productService.findProductSkuBySale();// 畅销商品
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkus);
        return Result.ok(indexVo);
    }

}
