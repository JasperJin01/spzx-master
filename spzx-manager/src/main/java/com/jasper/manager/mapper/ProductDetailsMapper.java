package com.jasper.manager.mapper;

import com.jasper.model.entity.product.ProductDetails;

public interface ProductDetailsMapper {
    void insertProductDetails(ProductDetails productDetails);

    ProductDetails selectByProductId(Long productId);
}
