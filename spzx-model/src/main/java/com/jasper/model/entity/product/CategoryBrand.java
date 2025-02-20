package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * 类别和品牌是 多对多 的关系
 * 一个类别可以有多个品牌，一个品牌可以有不同类别的商品
 */
@Data
public class CategoryBrand extends BaseEntity {
	
	private Long brandId;
	private Long categoryId;
    
    // 扩展的属性用来封装前端所需要的数据
	private String categoryName;
	private String brandName;
	private String logo; // 品牌logo
	
}