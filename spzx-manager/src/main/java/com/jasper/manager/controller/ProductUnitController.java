package com.jasper.manager.controller;


import com.jasper.manager.service.ProductUnitService;
import com.jasper.model.entity.product.ProductUnit;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品单位管理
 */
@RestController
@RequestMapping(value="/admin/product/productUnit")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class ProductUnitController {
    @Autowired
    ProductUnitService productUnitService;

    /**
     * 查询所有商品单位
     * @return
     */
    @GetMapping("findAll")
    public Result<List<ProductUnit>> findAll() {
        List<ProductUnit> productUnitList = productUnitService.selectAll();
        return Result.ok(productUnitList) ;
    }

}
