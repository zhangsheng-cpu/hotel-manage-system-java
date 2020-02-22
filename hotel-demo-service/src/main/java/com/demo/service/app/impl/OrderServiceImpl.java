package com.demo.service.app.impl;

import com.demo.dao.app.DinnerTableDao;
import com.demo.dao.app.FoodDao;
import com.demo.dao.app.OrderDao;
import com.demo.dao.app.OrderDetailDao;
import com.demo.entity.DinnerTableEntity;
import com.demo.entity.FoodEntity;
import com.demo.entity.OrderDetailEntity;
import com.demo.entity.OrderEntity;
import com.demo.service.app.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private DinnerTableDao dinnerTableDao;

    //事务
    @Transactional
    @Override
    public void order(int dinnerTableId, Map<Integer, Integer> shopCar, String total) {

        OrderEntity order = new OrderEntity();

        //随机数
        Random rand = new Random();

        //拼装订单 编码
        StringBuilder orderCode = new StringBuilder();
        orderCode.append("OP-");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        orderCode.append(dateFormat.format(new Date()));
        /*orderCode.append(rand.nextInt(900)+100);*/
        orderCode.append(UUID.randomUUID().toString());
        //订单编码
        order.setOrderCode(orderCode.toString());
        //订单的 餐桌
        order.setTableId(dinnerTableId);
        //总价
        order.setTotalPrice(Double.valueOf(total));

        orderDao.order(order);
        Integer orderId = order.getId();

        //获取购物车中所在的菜品id
        Set<Integer> foodIds = shopCar.keySet();
        for (Integer foodId : foodIds) {
            //购买数量
            Integer buyNum = shopCar.get(foodId);
            FoodEntity food = foodDao.findById(foodId);

            orderDetailDao.insert(orderId, foodId, buyNum, food.getDiscount());
        }
    }

    @Override
    public List<OrderEntity> findByTableId(int dinnerTableId) {
        //根据餐桌的id 查询所有未删除的  未付款的订单
        List<OrderEntity> orders = orderDao.findByTableId(dinnerTableId);

        //遍历所有的订单,
        if (orders != null && orders.size() > 0) {
            for (OrderEntity order : orders) {
                // 根据订单id 查询  订单明细与菜单
                List<OrderDetailEntity> details = orderDao.findByOrderId(order.getId());
                if (details != null && details.size() > 0) {
                    order.setOrderDetails(details);
                }
            }
        }
        return orders;
    }

    @Override
    public OrderEntity findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void update(OrderEntity order) {
        orderDao.update(order);
        //已付完款，用户走了，餐桌状态该为未使用
        DinnerTableEntity dinnerTable = dinnerTableDao.findById(order.getTableId());
        dinnerTable.setTableStatus(0);

        dinnerTableDao.update(dinnerTable);
    }

    @Override
    public void updateOrder(OrderEntity order) {
        orderDao.updateOrder(order);
        //取消订单，用户走了，餐桌状态该为未使用
        DinnerTableEntity dinnerTable = dinnerTableDao.findById(order.getTableId());
        dinnerTable.setTableStatus(0);

        dinnerTableDao.update(dinnerTable);
    }

    @Override
    public List<OrderEntity> findAll() {
        //根据餐桌的id查询所有未删除的未付款的订单
        List<OrderEntity>  orders = orderDao.findAll();

        //遍历所有的订单，根据订单id查询其对于订单明细
        if(orders != null && orders.size()>0) {
            for (OrderEntity order : orders) {
                List<OrderDetailEntity>  details = orderDao.findByOrderId(order.getId());
                if(details != null && details.size()>0) {
                    order.setOrderDetails(details);
                }
                //通过餐桌的id查询餐桌
                DinnerTableEntity dinnerTable = dinnerTableDao.findById(order.getTableId());

                order.setDinnerTable(dinnerTable);
            }
        }

        return orders;
    }
}
