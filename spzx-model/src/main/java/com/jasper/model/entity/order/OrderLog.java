package com.jasper.model.entity.order;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class OrderLog extends BaseEntity {

   private static final long serialVersionUID = 1L;

   private Long orderId;

   private String operateUser;

   private Integer processStatus;

   private String note;

}