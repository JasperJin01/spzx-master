package com.jasper.model.entity.order;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem extends BaseEntity {

   private static final long serialVersionUID = 1L;

   private Long orderId;

   private Long skuId;

   private String skuName;

   private String thumbImg;

   private BigDecimal skuPrice;

   private Integer skuNum;

}