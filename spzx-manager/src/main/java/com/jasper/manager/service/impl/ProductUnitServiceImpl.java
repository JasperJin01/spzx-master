package com.jasper.manager.service.impl;

import com.jasper.manager.mapper.ProductUnitMapper;
import com.jasper.manager.service.ProductUnitService;
import com.jasper.model.entity.product.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> selectAll() {
        return productUnitMapper.selectAll();
    }
}
