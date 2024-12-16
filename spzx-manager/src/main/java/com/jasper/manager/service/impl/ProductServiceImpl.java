package com.jasper.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.ProductDetailsMapper;
import com.jasper.manager.mapper.ProductMapper;
import com.jasper.manager.mapper.ProductSkuMapper;
import com.jasper.manager.service.ProductService;
import com.jasper.model.dto.product.ProductDto;
import com.jasper.model.entity.product.Product;
import com.jasper.model.entity.product.ProductDetails;
import com.jasper.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductSkuMapper productSkuMapper;

    @Autowired
    ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page,limit);
        List<Product> products = productMapper.selectByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    public void saveProduct(Product product) {

        //保存product，返回主键
        product.setAuditStatus(0); // 默认待审核
        product.setAuditMessage("商品待审核");// 默认商品待审核
        product.setStatus(0);// 1 表示上架
        productMapper.insertProduct(product);
        Long productId = product.getId();

        //根据主键，保存product_sku
        List<ProductSku> productSkuList = product.getProductSkuList();
        int i = 0;
        for (ProductSku productSku : productSkuList) {
            i++;
            productSku.setProductId(productId);
            productSku.setSkuCode(productId+"_"+i);
            productSku.setSkuName(product.getName()+" " +productSku.getSkuSpec());
            productSkuMapper.insertProductSku(productSku);
        }


        //根据主键，保存product_details
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId);
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.insertProductDetails(productDetails);

    }

    @Override
    public Product getById(Long productId) {

        Product product = productMapper.selectById(productId);

        // 封装sku
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(productId);
        product.setProductSkuList(productSkuList);

        // 封装海报
        ProductDetails productDetails = productDetailsMapper.selectByProductId(productId);
        product.setDetailsImageUrls(productDetails.getImageUrls());

        return product;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        productMapper.updateStatus(id,status);
    }

    @Override
    public void updateAuditStatus(Long id, Integer status) {
        String message = status==1?"审核通过":"审核不通过";
        productMapper.updateAuditStatus(id,status,message);
    }
}
