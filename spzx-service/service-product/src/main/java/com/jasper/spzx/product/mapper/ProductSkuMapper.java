package com.jasper.spzx.product.mapper;

import com.jasper.model.dto.product.ProductSkuDto;
import com.jasper.model.entity.product.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    /**
     * 畅销商品
     * @return
     */
    List<ProductSku> selectProductSkuBySale();

    List<ProductSku> selectByPage(ProductSkuDto productSkuDto);

    ProductSku selectById(Long skuId);

    List<ProductSku> selectByProductId(Long productId);
}
