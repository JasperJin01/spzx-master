package com.jasper.model.entity.product;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSku extends BaseEntity {

	private String skuCode;					// 商品编号，似乎是 productId_序号 的组合
	private String skuName;					// sku名称 例如：荣耀nova11 白色 + 8G
	private Long productId;
	private String thumbImg;				// 缩略图路径
	private BigDecimal salePrice;			// 售价
	private BigDecimal marketPrice;			// 市场价
	private BigDecimal costPrice;			// 成本价
	private Integer stockNum;				// 库存数量
	private Integer saleNum;				// 销量
	private String skuSpec;					// sku规格信息
	private String weight;					// 重量
	private String volume;					// 体积
	private Integer status;					// 线上状态：0-初始值，1-上架，-1-自主下架

}