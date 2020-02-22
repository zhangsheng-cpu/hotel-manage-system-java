package com.demo.controller.sys;

import com.demo.entity.DinnerTableEntity;
import com.demo.service.sys.DinnerTableSysService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class DinnerTableSysController {


    @Autowired
    private DinnerTableSysService dinnerTableSysService;

    @RequestMapping("/dinnerTable/list")
    public String dinnerTableSys(String keyword, String tableStatus, String disabled,
                                 @RequestParam(value = "pageNum",
                                         required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize",
                                         required = false, defaultValue = "10") int pageSize,
                                 Model model) {
        //查询餐桌
        List<DinnerTableEntity> dinnerTables = dinnerTableSysService.find(keyword, tableStatus, disabled, pageNum, pageSize);
        //页面信息
        PageInfo pageInfo = new PageInfo(dinnerTables);

        //保存餐桌
        model.addAttribute("dinnerTables", dinnerTables);
        model.addAttribute("keyword", keyword);
        model.addAttribute("tableStatus", tableStatus);
        model.addAttribute("disabled", disabled);

        model.addAttribute("list", pageInfo);
        return "sys/dinnerTableList";
    }

    //表示更新，即删除和激活
    @RequestMapping("/dinnerTable/update")
    public String update(String id, String disabled) {
        //通过ID查询餐桌
        DinnerTableEntity dinnerTable = dinnerTableSysService.findById(Integer.parseInt(id));

        dinnerTable.setDisabled(Integer.parseInt(disabled));

        //更新
        dinnerTableSysService.update(dinnerTable);
        //重定向
        return "redirect:/sys/dinnerTable/list";
    }

    //跳转到餐桌的添加页面
    @RequestMapping("/dinnerTable/addPage")
    public String addPage() {
        return "sys/dinnerTableAdd";
    }

    //ajax 判断餐桌是否存在
    @RequestMapping("/dinnerTable/addTable")
    public void add(String tableName, HttpServletResponse response) throws Exception{
        //根据餐桌的名字查寻餐桌
        DinnerTableEntity dinnerTable = dinnerTableSysService.findByTableName(tableName);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        //判断餐桌的名字是否存在
        if (dinnerTable == null) {
            //当前用户输入的餐桌名字目前数据库没有，则保存用户输入的餐桌名
            dinnerTableSysService.insert(tableName);
            out.println("success");
        } else {
            out.println("fail");
        }
    }

}
