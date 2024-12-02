package com.jasper.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jasper.manager.mapper.ProductSpecMapper;
import com.jasper.manager.service.ProductSpecService;
import com.jasper.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    ProductSpecMapper productSpecMapper;

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductSpec> productSpecs = productSpecMapper.selectByPage();
        return new PageInfo<>(productSpecs);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.insertProductSpec(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public List<ProductSpec> findAllProductSpec() {
        return productSpecMapper.selectAllProductSpec();
    }
}
