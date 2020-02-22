package com.demo.dao.app;

import com.demo.entity.OrderDetailEntity;


public interface OrderDetailDao {

    // 添加订单明细表
    void insert(Integer order, Integer food, Integer buyNum, double discount);
}
