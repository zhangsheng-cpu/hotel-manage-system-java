package com.demo.controller.sys;

import com.demo.entity.FoodTypeEntity;
import com.demo.service.sys.FoodTypeSysService;
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
public class FoodTypeSysController {

    @Autowired
    private FoodTypeSysService foodTypeSysService;

    @RequestMapping("/foodType/list")
    public String foodType(String disabled, String keyword,
                           @RequestParam(value = "pageNum",
                                   required = false, defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize",
                                   required = false, defaultValue = "10") int pageSize,
                           Model model) {
        //根据用户的输入和选择去查询菜系
        List<FoodTypeEntity> foodType = foodTypeSysService.find(keyword, disabled, pageNum, pageSize);

        model.addAttribute("foodTypes", foodType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("disabled", disabled);

        //分页
        PageInfo info = new PageInfo(foodType);
        model.addAttribute("list", info);

        return "sys/foodTypeList";
    }

    //跳转到菜系添加页面
    @RequestMapping("/foodType/addPage")
    public String addPage() {
        return "sys/foodTypeAdd";
    }

    //判断菜系是否存在
    @RequestMapping("/foodType/addSubmit")
    public void addSubmit(String foodTypeName, HttpServletResponse response) throws Exception {
        //根据菜系名字查找菜系
        FoodTypeEntity foodType = foodTypeSysService.findByFoodName(foodTypeName);

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        if (foodType == null) {
            foodTypeSysService.insert(foodTypeName);
            out.println("success");
        } else {
            out.println("fail");
        }
    }

    //删除或者激活
    @RequestMapping("/foodType/update")
    public String update(String disabled, String id) {
        //根据ID查找菜系
        FoodTypeEntity foodType2 = foodTypeSysService.findById(Integer.parseInt(id));
        System.out.println("菜系更改前：" + foodType2);
        foodType2.setDisabled(Integer.parseInt(disabled));
        System.out.println("菜系更改后：" + foodType2);

        foodTypeSysService.update(foodType2);

        return "redirect:/sys/foodType/list";
    }

    //更新菜系
    @RequestMapping("/foodType/viewUpdate")
    public String viewUpdate(String id, Model model) {
        FoodTypeEntity foodType = foodTypeSysService.findById(Integer.parseInt(id));

        model.addAttribute("foodType", foodType);
        //跳转到更新页面
        return "sys/foodTypeUpdate";
    }

    //判断菜系 是否存在
    @RequestMapping("/foodType/updateSubmit")
    public void updateSubmit(String foodTypeName, String id, HttpServletResponse response) throws Exception {
        //根据菜系名字查找菜系
        FoodTypeEntity foodType = foodTypeSysService.findByFoodName(foodTypeName);

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        //如果菜系为null，表现用户输入的菜系名字目前数据库还没有，可更新到数据库
        if (foodType == null) {
            //根据ID查找菜系
            FoodTypeEntity foodType2 = foodTypeSysService.findById(Integer.parseInt(id));

            foodType2.setTypeName(foodTypeName);

            foodTypeSysService.update(foodType2);
            out.println("success");
        } else {
            out.println("fail");
        }
    }

}
