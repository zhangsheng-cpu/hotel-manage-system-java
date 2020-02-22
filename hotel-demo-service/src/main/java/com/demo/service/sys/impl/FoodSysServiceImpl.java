package com.demo.service.sys.impl;

import com.demo.dao.sys.FoodSysDao;
import com.demo.entity.FoodEntity;
import com.demo.service.sys.FoodSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodSysServiceImpl implements FoodSysService {

	@Autowired
	private FoodSysDao foodDao;

	@Override
	public List<FoodEntity> find(String keyword, String foodTypeId, int pageNum, int pageSize) {
		return foodDao.find(keyword, foodTypeId, pageNum, pageSize);
	}

	@Override
	public FoodEntity findById(int id) {
		return foodDao.findById(id);
	}

	@Override
	public void update(FoodEntity food) {
		foodDao.update(food);
	}

	@Override
	public FoodEntity findByFoodName(String foodName) {
		return foodDao.findByFoodName(foodName);
	}

	@Override
	public void insert(FoodEntity food) {
		foodDao.insert(food);
	}
}
