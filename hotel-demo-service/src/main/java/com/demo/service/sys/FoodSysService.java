package com.demo.service.sys;

import com.demo.entity.FoodEntity;

import java.util.List;


public interface

FoodSysService {

	List<FoodEntity> find(String keyword, String foodTypeId, int pageNum, int pageSize);

	FoodEntity findById(int id);

	void update(FoodEntity food);

	FoodEntity findByFoodName(String foodName);

	void insert(FoodEntity food);

}
