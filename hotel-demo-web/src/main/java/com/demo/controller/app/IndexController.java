package com.demo.controller.app;

import com.demo.entity.DinnerTableEntity;
import com.demo.service.app.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class IndexController {

    @Autowired
    private DinnerTableService dinnerTableService;

    /*@RequestMapping("/index")
    public String getAll(String method, String tableStatus, String tableName, Model model) {

        List<DinnerTableEntity> dinnerTables = null;
        if (method.equals("submitTable")) {
            //表明是通过点击查看餐桌按钮来查询
            dinnerTables = dinnerTableService.getAll(tableStatus, tableName);
        } else {
            //项目一运行就查看未使用的餐桌
            //未使用的餐桌
            tableStatus = "0";
            dinnerTables = dinnerTableService.getAll(tableStatus, null);
        }
        model.addAttribute("tableStatus", tableStatus);
        model.addAttribute("dinnerTables", dinnerTables);
        return "app/index";
    }*/

    //项目一运行就查看未使用的餐桌
    @RequestMapping("/index")
    public String noUsed(String tableStatus, String tableName, Model model) {
        //状态设置为 未使用
        tableStatus = "0";
        List<DinnerTableEntity> dinnerTables = dinnerTableService.getAll(tableStatus, null);

        model.addAttribute("tableStatus", tableStatus);
        model.addAttribute("dinnerTables", dinnerTables);
        return "app/index";
    }

    //点击事件传值
    @RequestMapping("/index1")
    public String click(String tableStatus, String tableName, Model model) {
        List<DinnerTableEntity> dinnerTables = dinnerTableService.getAll(tableStatus, tableName);

        model.addAttribute("tableStatus", tableStatus);
        model.addAttribute("dinnerTables", dinnerTables);
        return "app/index";
    }
}
