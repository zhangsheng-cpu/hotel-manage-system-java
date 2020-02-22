package com.demo.controller.app;

import com.demo.entity.DinnerTableEntity;
import com.demo.entity.OrderEntity;
import com.demo.service.app.DinnerTableService;
import com.demo.service.app.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/app")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DinnerTableService dinnerTableService;

    @RequestMapping("/order/add")
    public String Order(String dinnerTableId, String total, HttpSession session, Model model) {

        //根据餐桌的id 获取其 购物车
        Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(dinnerTableId);

        if (shopCar != null && !shopCar.isEmpty()) {

            //保存到数据库
            orderService.order(Integer.parseInt(dinnerTableId), shopCar, total);

            //删除对应餐桌购物车中的商品
            session.removeAttribute(dinnerTableId);


            //订单表：订单id  订单编号   下单时间  总金额
            //菜品表：菜品名  菜品价格
            //订单明细表：购买数量  折后价（菜品价格*购买时折扣）
            //默认菜品价格不会变，只改折扣

            //跳转到订单详情页面  根据餐桌id查询该餐桌所有未删除的订单
            List<OrderEntity> orders = orderService.findByTableId(Integer.parseInt(dinnerTableId));


            //当前订单是哪一个餐桌的
            DinnerTableEntity dinnerTable = dinnerTableService.findById(Integer.parseInt(dinnerTableId));

            //订单
            model.addAttribute("orders", orders);
            //餐桌
            model.addAttribute("dinnerTable", dinnerTable);

            return "app/orderItem";
        } else {
            //购物车中没有商品，回到点餐页面
            return "redirect:menu?id=" + dinnerTableId;
        }
    }

    //付款
    @RequestMapping("/order/pay")
    public String pay(String orderId) {
        //付款 订单的状态 orderStatus=1 付款时间
        //餐桌的 状态 ----没有使用
        OrderEntity order = orderService.findById(Integer.parseInt(orderId));
        System.out.println("order改前:" + order);

        order.setOrderStatus(1);

        System.out.println("order改后:" + order);

        orderService.update(order);
        return "redirect:/app/index1";
    }

    //取消订单
    @RequestMapping("/order/delete")
    public String cancel(String orderId) {
        //取消订单需做以下两个点
        //①将订单表中的disabled=1 删除      ②将对于餐桌tableStaus=0 未使用
        OrderEntity order = orderService.findById(Integer.parseInt(orderId));
        System.out.println("order改前：" + order);

        order.setDisabled(1);

        System.out.println("order改后：" + order);
        orderService.updateOrder(order);

        return "redirect:/app/index1";
    }

    //查询所有订单
    @RequestMapping("/order/list")
    public String selectAll(Model model) {
        List<OrderEntity> all = orderService.findAll();

        model.addAttribute("orders", all);

        return "app/orderList";
    }

}
