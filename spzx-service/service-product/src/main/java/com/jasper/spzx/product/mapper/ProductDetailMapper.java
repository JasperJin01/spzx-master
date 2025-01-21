package com.jasper.spzx.product.mapper;

import com.jasper.model.entity.product.ProductDetails;

public interface ProductDetailMapper {
    ProductDetails selectByProductId(Long productId);
}
