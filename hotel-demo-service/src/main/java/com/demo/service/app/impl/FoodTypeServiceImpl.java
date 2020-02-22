package com.demo.service.app.impl;

import com.demo.dao.app.FoodTypeDao;
import com.demo.entity.FoodTypeEntity;
import com.demo.service.app.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    @Autowired
    private FoodTypeDao dao;

    @Override
    public List<FoodTypeEntity> findAll() {
        return dao.findAll();
    }
}
