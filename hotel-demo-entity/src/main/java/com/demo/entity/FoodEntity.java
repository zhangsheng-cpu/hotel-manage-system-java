package com.demo.entity;


import lombok.Data;

import java.util.Date;

@Data
public class FoodEntity {

    private int id; //主键
    private String foodName; //菜名称
    private int foodTypeId; // 所属菜系 ，外键字段
    private double price; //    价格
    private double discount; // 折扣
    private String remark;//    简介
    private String img; //      图片
    private Integer buyNum;//   购买数量，只做记录数据 不保存到数据库

    private Date createDate;
    private Date updateDate;
    private Integer disabled;

    private FoodTypeEntity foodType;

    public FoodEntity() {
    }

    public FoodEntity(String foodName, int foodTypeId, double price, double discount, String remark, String img, Integer disabled) {
        this.foodName = foodName;
        this.foodTypeId = foodTypeId;
        this.price = price;
        this.discount = discount;
        this.remark = remark;
        this.img = img;
        this.disabled = disabled;
    }

    public FoodEntity(int id, String foodName, int foodTypeId, double price, double discount, String remark, String img, Integer buyNum, Date createDate, Date updateDate, Integer disabled, FoodTypeEntity foodType) {
        this.id = id;
        this.foodName = foodName;
        this.foodTypeId = foodTypeId;
        this.price = price;
        this.discount = discount;
        this.remark = remark;
        this.img = img;
        this.buyNum = buyNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.disabled = disabled;
        this.foodType = foodType;
    }
}
