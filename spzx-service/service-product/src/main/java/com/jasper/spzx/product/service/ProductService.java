package com.jasper.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.product.ProductItemVo;
import com.jasper.model.dto.product.ProductSkuDto;
import com.jasper.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);
}
