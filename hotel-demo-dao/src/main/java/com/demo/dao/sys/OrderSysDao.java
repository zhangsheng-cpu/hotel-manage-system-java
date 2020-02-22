package com.demo.dao.sys;

import com.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSysDao {

    List<OrderEntity> find(@Param("PageNum")int pageNum, @Param("PageSize")int pageSize);

    OrderEntity findById(int id);

    void update(OrderEntity order);


}
