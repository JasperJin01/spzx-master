package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductSpec extends BaseEntity {

	private String specName;   // 规格名称
	private String specValue;  // 规格值

}