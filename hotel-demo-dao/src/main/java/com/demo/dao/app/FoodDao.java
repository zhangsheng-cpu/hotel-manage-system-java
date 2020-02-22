package com.demo.dao.app;

import com.demo.entity.FoodEntity;

import java.util.List;

public interface FoodDao {

    List<FoodEntity> findByFoodTypeId(Integer foodTypeId);

    FoodEntity findById(Integer foodId);
}
