package com.jasper.model.entity.order;

import com.jasper.model.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderInfo extends BaseEntity {

    private Long userId;
    private String nickName;
    private String orderNo;
    private Long couponId;
    private BigDecimal totalAmount;
    private BigDecimal couponAmount;
    private BigDecimal originalTotalAmount;
    private BigDecimal feightFee;
    private Integer payType;
    private Integer orderStatus;
    private String receiverName;
    private String receiverPhone;
    private String receiverTagName;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private Date paymentTime;
    private Date deliveryTime;
    private Date receiveTime;
    private String remark;
    private Date cancelTime;
    private String cancelReason;

    // 订单详情
    private List<OrderItem> orderItemList;

}