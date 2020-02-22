package com.demo.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/app")
public class ShopCarController {

    @RequestMapping("/shopCar/add")
    public String shopCar(String foodId, String dinnerTableId, HttpSession session) {
        //商品id
        Integer foodIdInt = Integer.parseInt(foodId);
        //根据餐桌的id 获取其购物车
        Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(dinnerTableId);

        //添加商品到购物车
        if (shopCar != null) {
            //当前餐桌的购物车已经有商品
            //拿到当前餐桌购物车中所有的菜品id
            Set<Integer> foodIds = shopCar.keySet();
            //判断当前购物车中，是否已经有当前需要加入到购物车中的商品
            if (foodIds.contains(foodIdInt)) {
                Integer buyNum2 = shopCar.get(foodIdInt);
                shopCar.put(foodIdInt, buyNum2 + 1);
            } else {
                //第一次加入该商品到当前餐桌的购物车
                shopCar.put(foodIdInt, 1);
            }
        } else {
            //新建购物车 key为商品id  value为购买数量
            shopCar = new HashMap<>();
            shopCar.put(foodIdInt, 1);
            session.setAttribute(dinnerTableId, shopCar);
        }
        return "redirect:/app/menu?id=" + dinnerTableId;
    }

    @RequestMapping("/shopCar/update")
    public void update(String foodId, String buyNum, String dinnerTableId, HttpSession session) {
        //商品id
        Integer foodIdInt = Integer.parseInt(foodId);
        //根据餐桌的id 获取其购物车
        Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(dinnerTableId);
        //更新购物车
        shopCar.put(foodIdInt, Integer.parseInt(buyNum));
    }

    @RequestMapping("/shopCar/delete")
    public String delete(String dinnerTableId, String foodId, HttpSession session) {
        //商品id
        Integer foodIdInt = Integer.parseInt(foodId);
        //根据餐桌的id 获取其购物车
        Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(dinnerTableId);
        //删除餐桌中的某个菜品
        //根据餐桌的id获取其购物车
        shopCar.remove(foodIdInt);

        return "redirect:/app/menu?id=" + dinnerTableId;
    }
}
