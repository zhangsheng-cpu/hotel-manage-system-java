package com.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {

    private String loginName; //用户名
    private String passWord;    //密码
    private String email;       //邮箱
    private String phone;       //手机号
    private Date createDate;
    private int disabled;//0：新建   1：已激活
}
