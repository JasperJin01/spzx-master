package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}