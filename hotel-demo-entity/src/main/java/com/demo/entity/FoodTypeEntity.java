package com.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FoodTypeEntity {

    private int id;
    private String typeName; //菜系名称
    private Date createDate;
    private Date updateDate;
    private Integer disabled;

}
