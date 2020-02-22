package com.demo.controller.app;

import com.demo.entity.DinnerTableEntity;
import com.demo.service.app.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 过往云烟er~
 */

@Controller
@RequestMapping("/app")
public class DinnerTableController {


    @Autowired
    private DinnerTableService dinnerTableService;

    @RequestMapping("/dinnerTable")
    public String dinnerTable(Integer tableStatus, Integer dinnerTableId) {
        //通过 Id 查询餐桌信息
        DinnerTableEntity dinnerTable = dinnerTableService.findById(dinnerTableId);

        //修改 餐桌 状态
        dinnerTable.setTableStatus(tableStatus);

        //修改餐桌信息
        dinnerTableService.update(dinnerTable);

        return "redirect:menu?id=" + dinnerTableId;
    }
}
