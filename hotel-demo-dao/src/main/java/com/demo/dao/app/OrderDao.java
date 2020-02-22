package com.demo.dao.app;

import com.demo.entity.OrderDetailEntity;
import com.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    // 添加 订单表.
    void order(OrderEntity order);

    //通过餐桌的id查询未付款的订单
    List<OrderEntity> findByTableId(int dinnerTableId);

    //通过订单 id查询 订单明细表
    List<OrderDetailEntity> findByOrderId(Integer orderId);

    //通过id 查找 订单表
    OrderEntity findById(int id);

    //付款
    void update(OrderEntity order);

    // 取消 订单
    void updateOrder(OrderEntity order);

    //查询 订单表
    List<OrderEntity> findAll();

}
