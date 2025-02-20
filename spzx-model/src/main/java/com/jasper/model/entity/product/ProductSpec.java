package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * 商品规格
 * 
 * 使用了序列化保存商品的规格值(specValue)
 * 例如：
 * specName: "T恤"
 * specValue: "[{"key":"颜色","valueList":["白色","黑色","红色"]},{"key":"尺码","valueList":...}]"
 */
@Data
public class ProductSpec extends BaseEntity {

	private String specName;   // 规格名称
	private String specValue;  // 规格值

}