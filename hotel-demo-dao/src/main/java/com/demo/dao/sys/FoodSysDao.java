package com.demo.dao.sys;

import com.demo.entity.FoodEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodSysDao {

    List<FoodEntity> find(String keyword, String foodTypeId, @Param("pageNum")int pageNum, @Param("pageSize")int pageSize);

    FoodEntity findById(Integer foodId);

    void update(FoodEntity food);

    FoodEntity findByFoodName(String foodName);

    void insert(FoodEntity food);
}
