package com.demo.service.sys;

import com.demo.entity.OrderEntity;
import java.util.List;


public interface OrderSysService {

	List<OrderEntity> find(int pageNum, int pageSize);

	OrderEntity findById(int id);

	void update(OrderEntity order);

}
