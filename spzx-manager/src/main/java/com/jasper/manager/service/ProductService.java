package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.product.ProductDto;
import com.jasper.model.entity.product.Product;

public interface ProductService {
    Product getById(Long id);

    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void saveProduct(Product product);

    void updateStatus(Long id, Integer status);

    void updateAuditStatus(Long id, Integer status);
}
