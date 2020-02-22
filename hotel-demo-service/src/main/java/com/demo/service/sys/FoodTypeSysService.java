package com.demo.service.sys;

import com.demo.entity.FoodTypeEntity;

import java.util.List;

public interface FoodTypeSysService {

	List<FoodTypeEntity> find(String keyword, String disabled, int pageNum, int pageSize);

	FoodTypeEntity findByFoodName(String foodTypeName);

	void insert(String foodType2);

	FoodTypeEntity findById(int id);

	void update(FoodTypeEntity foodType2);


}
