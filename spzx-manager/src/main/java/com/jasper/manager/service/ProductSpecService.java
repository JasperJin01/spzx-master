package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    List<ProductSpec> findAllProductSpec();
}
