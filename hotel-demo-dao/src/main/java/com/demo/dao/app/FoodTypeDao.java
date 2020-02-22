package com.demo.dao.app;

import com.demo.entity.FoodTypeEntity;

import java.util.List;

public interface FoodTypeDao {

    List<FoodTypeEntity> findAll();
}
