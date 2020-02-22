package com.demo.controller.sys;

import com.demo.entity.OrderEntity;
import com.demo.service.sys.OrderSysService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/sys")
public class OrderSysController {

    @Autowired
    private OrderSysService orderSysService;

    @RequestMapping("/order/list")
    public String order(
            @RequestParam(value = "pageNum",
                    required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize",
                    required = false, defaultValue = "10") int pageSize,
            Model model) {
        //查询所有的订单
        List<OrderEntity> order = orderSysService.find(pageNum, pageSize);

        PageInfo pageInfo = new PageInfo(order);
        model.addAttribute("list", pageInfo);

        return "sys/orderList";
    }

    @RequestMapping("/order/update")
    public String update(String id, String disabled) {
        //删除或激活
        OrderEntity  order = orderSysService.findById(Integer.parseInt(id));

        order.setDisabled(Integer.parseInt(disabled));

        orderSysService.update(order);
        return "redirect:/sys/order/list";
    }
}
