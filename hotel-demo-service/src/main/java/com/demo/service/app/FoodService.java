package com.demo.service.app;

import com.demo.entity.FoodEntity;

import java.util.List;

public interface FoodService {

    List<FoodEntity> findByFoodTypeId(Integer foodTypeId);

    FoodEntity findById(Integer foodId);
}
