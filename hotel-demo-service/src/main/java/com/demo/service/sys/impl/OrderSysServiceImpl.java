package com.demo.service.sys.impl;


import com.demo.dao.sys.OrderSysDao;
import com.demo.entity.OrderEntity;
import com.demo.service.sys.OrderSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSysServiceImpl implements OrderSysService {

	@Autowired
	private OrderSysDao orderDao;

	@Override
	public List<OrderEntity> find(int pageNum, int pageSize) {
		return orderDao.find(pageNum, pageSize);
	}

	@Override
	public OrderEntity findById(int id) {
		return orderDao.findById(id);
	}

	@Override
	public void update(OrderEntity order) {
		orderDao.update(order);
	}
}
