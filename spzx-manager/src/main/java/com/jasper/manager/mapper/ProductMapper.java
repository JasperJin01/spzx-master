package com.jasper.manager.mapper;

import com.jasper.model.dto.product.ProductDto;
import com.jasper.model.entity.product.Product;

import java.util.List;

public interface ProductMapper {
    Product selectById(Long productId);

    List<Product> selectByPage(ProductDto productDto);

    void insertProduct(Product product);

    void updateStatus(Long id, Integer status);

    void updateAuditStatus(Long id, Integer status, String message);
}
