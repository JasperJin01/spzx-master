package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Brand extends BaseEntity {

	private String name;
	private String logo;

}