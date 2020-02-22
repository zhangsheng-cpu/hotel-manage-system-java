package com.demo.service.app.impl;

import com.demo.dao.app.FoodDao;
import com.demo.entity.FoodEntity;
import com.demo.service.app.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDao dao;

    @Override
    public List<FoodEntity> findByFoodTypeId(Integer foodTypeId) {
        return dao.findByFoodTypeId(foodTypeId);
    }

    @Override
    public FoodEntity findById(Integer foodId) {
        return dao.findById(foodId);
    }
}
