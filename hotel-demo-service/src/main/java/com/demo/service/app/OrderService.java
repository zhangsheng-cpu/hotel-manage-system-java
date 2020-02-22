package com.demo.service.app;

import com.demo.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {

    // 添加 订单表. 订单明细表 与购物车
    void order(int dinnerTableId, Map<Integer, Integer> shopCar, String total);

    //通过餐桌的id查询未付款的订单
    List<OrderEntity> findByTableId(int dinnerTableId);

    //通过id 查找 订单表
    OrderEntity findById(int id);

    //付款  付完款之后 修改餐桌状态 为 未使用
    void update(OrderEntity order);

    // 取消 订单之后 修改餐桌状态 为 未使用
    void updateOrder(OrderEntity order);

    //查询 订单表
    List<OrderEntity> findAll();

}
