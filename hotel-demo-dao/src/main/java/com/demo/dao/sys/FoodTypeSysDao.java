package com.demo.dao.sys;

import com.demo.entity.FoodTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodTypeSysDao {

    List<FoodTypeEntity> find(String keyword, String disabled, @Param("pageNum")int pageNum, @Param("pageSize")int pageSize);

    FoodTypeEntity findByFoodName(String foodTypeName);

    void insert(String foodTypeName);

    FoodTypeEntity findById(int id);

    void update(FoodTypeEntity foodType2);

}
