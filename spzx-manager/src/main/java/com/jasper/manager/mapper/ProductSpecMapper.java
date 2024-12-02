package com.jasper.manager.mapper;

import com.jasper.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecMapper {
    List<ProductSpec> selectByPage();

    void insertProductSpec(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    List<ProductSpec> selectAllProductSpec();
}
