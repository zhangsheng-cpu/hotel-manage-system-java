package com.demo.controller.app;


import com.demo.entity.FoodEntity;
import com.demo.entity.FoodTypeEntity;
import com.demo.service.app.FoodService;
import com.demo.service.app.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class MenuListController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodTypeService foodTypeService;

    @RequestMapping("/menuList")
    public String menuList(String foodTypeId, Model model) {

        List<FoodTypeEntity> foodTypes = foodTypeService.findAll();

        if (foodTypeId == null || foodTypeId.equals("")) {
            //默认查询所有 菜系中 第一个删除菜系的菜品
            Integer foodTypeInt = foodTypes.get(0).getId();

            foodTypeId = Integer.toString(foodTypeInt);
        }

        List<FoodEntity> foods = foodService.findByFoodTypeId(Integer.parseInt(foodTypeId));

        model.addAttribute("foodTypes", foodTypes);
        model.addAttribute("foods", foods);
        return "app/menuList";
    }
}
