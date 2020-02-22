package com.demo.controller.app;

import com.demo.entity.DinnerTableEntity;
import com.demo.entity.FoodEntity;
import com.demo.entity.FoodTypeEntity;
import com.demo.entity.OrderEntity;
import com.demo.service.app.DinnerTableService;
import com.demo.service.app.FoodService;
import com.demo.service.app.FoodTypeService;
import com.demo.service.app.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/app")
public class MenuController {

    @Autowired
    private DinnerTableService dinnerTableService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/menu")
    public String Menu(String foodTypeId, /*餐桌的id*/String id, HttpSession session, Model model) {

        //通过餐桌的id 查询餐桌
        DinnerTableEntity dinnerTable = dinnerTableService.findById(Integer.parseInt(id));

        model.addAttribute("dinnerTable", dinnerTable);

        //判断该餐桌是否有未付款的订单，如有则跳转到订单详情页面去付款，如没有则跳转到正常的点餐页面
        List<OrderEntity> orders = orderService.findByTableId(Integer.parseInt(id));
        if (orders != null && orders.size() > 0) {
            model.addAttribute("orders", orders);
            return "app/orderItem";
        } else {
            //查找 所有未删除的 菜系 名称
            List<FoodTypeEntity> foodTypes = foodTypeService.findAll();

            if (foodTypeId == null || foodTypeId.equals("")) {
                // 默认查询所有菜系中第一个未删除菜系的菜品
                Integer foodTypeInt = foodTypes.get(0).getId();
                foodTypeId = Integer.toString(foodTypeInt);
            }
            List<FoodEntity> foods = foodService.findByFoodTypeId(Integer.parseInt(foodTypeId));

            //左边餐桌的购物车展示

            //通过餐桌的id获取其购物车，   购物车  key为商品id  value为购买数量
            Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(id);
            List<FoodEntity> foods2 = new ArrayList<>();
            //计算购物车中商品总金额
            Double total = 0.00;
            if (shopCar != null) {
                //拿到当前餐桌购物车中所在的菜品id
                Set<Integer> foodIds = shopCar.keySet();
                for (Integer foodId : foodIds) {
                    //通过菜品的id查询菜品
                    FoodEntity food = foodService.findById(foodId);
                    System.out.println("通过id查询food:" + food);

                    //通过购物车的key 获取value, 即购买数量
                    Integer buyNum = shopCar.get(foodId);
                    food.setBuyNum(buyNum);
                    foods2.add(food);

                    //当前商品购买需要的价格
                    Double price = food.getBuyNum() * food.getPrice() * food.getDiscount();
                    total = total + price;
                }
            }
            //所有未删除的 菜系
            model.addAttribute("foodTypes", foodTypes);
            // 默认查询所有菜系中第一个未删除菜系的菜品
            model.addAttribute("foods", foods);
            // 菜品 包括 购买数量 但不保存数据库
            model.addAttribute("foods2", foods2);
            // 商品的总金额
            model.addAttribute("total", total);
            return "app/menu";
        }
    }
}
