package com.demo.service.sys.impl;

import com.demo.dao.sys.FoodTypeSysDao;
import com.demo.entity.FoodTypeEntity;
import com.demo.service.sys.FoodTypeSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeSysServiceImpl implements FoodTypeSysService {

	@Autowired
	private FoodTypeSysDao foodTypeDao;

	@Override
	public List<FoodTypeEntity> find(String keyword, String disabled, int pageNum, int pageSize) {
		return foodTypeDao.find(keyword, disabled, pageNum, pageSize);
	}

	@Override
	public FoodTypeEntity findByFoodName(String foodTypeName) {
		return foodTypeDao.findByFoodName(foodTypeName);
	}

	@Override
	public void insert(String foodType2) {
		foodTypeDao.insert(foodType2);
	}

	@Override
	public FoodTypeEntity findById(int id) {
		return foodTypeDao.findById(id);
	}

	@Override
	public void update(FoodTypeEntity foodType2) {
		foodTypeDao.update(foodType2);
	}
}
