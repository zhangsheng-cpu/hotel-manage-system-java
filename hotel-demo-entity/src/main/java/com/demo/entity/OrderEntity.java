package com.demo.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderEntity {

    private Integer id;
    private String orderCode;//订单编号
    private Integer tableId;//订单是哪一餐桌的
    private Double totalPrice;//订单总金额
    private Integer orderStatus;//订单的状态 0表示未付款  1表示已付款
    private Date orderDate;//下单，提交购物车的时间
    private Date payDate;//付款的时间
    private Date updateDate;//最后更新的时间，菜品信息更改的时间
    private Integer disabled;//0 :未删除 1： 已删

    private DinnerTableEntity dinnerTable;
    private List<OrderDetailEntity> orderDetails;
}
