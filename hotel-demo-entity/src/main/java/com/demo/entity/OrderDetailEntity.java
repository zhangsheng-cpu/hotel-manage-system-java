package com.demo.entity;

import lombok.Data;

@Data
public class OrderDetailEntity {

    private Integer id;
    private Integer orderId; //订单id
    private Integer foodId;  //菜品id
    private Integer buyNum;   //购买数量
    private Double discount;   //当时客户购买的折扣
    private Integer disabled;//菜品是否在下单后又删除 1删除 0未删除

    private FoodEntity food;


}
