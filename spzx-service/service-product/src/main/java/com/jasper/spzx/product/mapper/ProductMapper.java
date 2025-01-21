package com.jasper.spzx.product.mapper;

import com.jasper.model.entity.product.Product;

public interface ProductMapper {
    Product selectById(Long productId);
}
