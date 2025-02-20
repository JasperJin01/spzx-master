package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * 商品 和 详情图片地址(URL)
 * fixme 似乎是 1对多 的关系，但是在表中，存储URL是间隔,存储的
 *  为什么不把这个放到product的一个字段里面去呢？
 */
@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}