package com.jasper.manager.mapper;

import com.jasper.model.entity.product.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> findByPage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);
}
