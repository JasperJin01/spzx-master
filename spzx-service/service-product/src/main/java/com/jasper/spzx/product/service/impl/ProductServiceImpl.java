package com.jasper.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.model.dto.product.ProductItemVo;
import com.jasper.model.dto.product.ProductSkuDto;
import com.jasper.model.entity.product.Product;
import com.jasper.model.entity.product.ProductDetails;
import com.jasper.model.entity.product.ProductSku;
import com.jasper.spzx.product.mapper.ProductDetailMapper;
import com.jasper.spzx.product.mapper.ProductMapper;
import com.jasper.spzx.product.mapper.ProductSkuMapper;
import com.jasper.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productSkuMapper.selectProductSkuBySale();
        return productSkuList;
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);
        List<ProductSku> productSkuList = productSkuMapper.selectByPage(productSkuDto);
        PageInfo<ProductSku> pageInfo = new PageInfo<>(productSkuList);
        return pageInfo;
    }

    @Override
    public ProductItemVo item(Long skuId) {

        // sku
        ProductSku productSku = productSkuMapper.selectById(skuId);

        // product
        Long productId = productSku.getProductId();
        Product product = productMapper.selectById(productId);

        // 轮播图
        String sliderUrls = product.getSliderUrls();

        // 详情
        ProductDetails productDetails = productDetailMapper.selectByProductId(productId);

        // 规格
        String specValue = product.getSpecValue();

        // FIXME 买看懂
        // sku对应规格
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(productId);
        // 为了页面根据规格组合切换sku
        Map<String,Object> map = new HashMap<>();
        for (ProductSku sku : productSkuList) {
            map.put(sku.getSkuSpec(),sku.getId());
        }

        // 封装数据
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        String[] sliderSplit = sliderUrls.split(",");
        productItemVo.setSliderUrlList(Arrays.asList(sliderSplit));
        String imageUrls = productDetails.getImageUrls();
        String[] imageSplit = imageUrls.split(",");
        productItemVo.setDetailsImageUrlList(Arrays.asList(imageSplit));
        productItemVo.setSpecValueList(JSON.parseArray(specValue));
        productItemVo.setSkuSpecValueMap(map);
        return productItemVo;
    }
}
