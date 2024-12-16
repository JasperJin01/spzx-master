package com.jasper.manager.mapper;

import com.jasper.model.entity.product.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    void insertProductSku(ProductSku productSku);

    List<ProductSku> selectByProductId(Long productId);
}
