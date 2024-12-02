package com.jasper.manager.service;

import com.github.pagehelper.PageInfo;
import com.jasper.model.entity.product.Brand;

public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);
}
