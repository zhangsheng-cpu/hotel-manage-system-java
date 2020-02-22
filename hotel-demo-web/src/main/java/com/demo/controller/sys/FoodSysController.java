package com.demo.controller.sys;

import com.demo.entity.FoodEntity;
import com.demo.entity.FoodTypeEntity;
import com.demo.service.sys.FoodSysService;
import com.demo.service.sys.FoodTypeSysService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/sys")
public class FoodSysController {

    @Autowired
    private FoodTypeSysService foodTypeSysService;

    @Autowired
    private FoodSysService foodSysService;

    public static final String filePath = "E:/png/1";

    @RequestMapping("/food/list")
    public String food(String keyword, String foodTypeId,
                       @RequestParam(value = "pageNum",
                               required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize",
                               required = false, defaultValue = "10") int pageSize,
                       Model model) {
        //查询所有的 菜系名
        List<FoodTypeEntity> foodTypes = foodTypeSysService.find(null, null, 1, 10);
        model.addAttribute("foodTypes", foodTypes);

        //根据关键字 和 菜品类型 查询菜品
        List<FoodEntity> foods = foodSysService.find(keyword, foodTypeId, pageNum, pageSize);
        //分页
        PageInfo pageInfo1 = new PageInfo(foods);
        model.addAttribute("list", pageInfo1);

        model.addAttribute("keyword", keyword);
        model.addAttribute("foodTypeId", foodTypeId);

        return "sys/foodList";
    }

    //激活或删除
    @RequestMapping("/food/update")
    public String update(String id, String disabled) {
        //通过ID查询餐桌
        FoodEntity food = foodSysService.findById(Integer.parseInt(id));

        food.setDisabled(Integer.parseInt(disabled));

        foodSysService.update(food);

        return "redirect:/sys/food/list";
    }

    //跳转到添加页面
    @RequestMapping("/food/addPage")
    public String addPage(Model model) {
        //查询所有的 菜系名
        List<FoodTypeEntity> foodTypes = foodTypeSysService.find(null, null, 1, 10);
        model.addAttribute("foodTypes", foodTypes);
        return "sys/foodAdd";
    }

    @RequestMapping("/food/insert")
    public String insert(FoodEntity foodEntity, /*上传的文件 */MultipartFile myfile) {
        //图片名

        /*String fileName = myfile.getOriginalFilename();
        //为了防止用户上传的图片名重复，一般上传图片是会给图片重新取一个不会重复的名字     不会重复的名字.jpg
        String extName = fileName.substring(fileName.lastIndexOf("."));
        //不会重复的名字
        String name = UUID.randomUUID().toString();
        //拼装新的不会重复的名字
        StringBuffer newName = new StringBuffer();
        newName.append(name).append(extName);*/
        //将用户上传的图片写到指定的目录
        String path = filePath + File.separator /*+ newName*/;
        File file1 = new File(path);
        try {
            myfile.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (foodEntity.getDiscount() == 0) {
            foodEntity.setDiscount(1);
        }
        //存入图片名
        foodEntity.setImg(path);
        //保存菜品相关信息到数据库
        foodSysService.insert(foodEntity);

        return "redirect:/sys/food/list";
    }

    //修改
    @RequestMapping("/food/viewUpdate")
    public String viewUpdate(String id, Model model) {
        //查询所有的 菜系名
        List<FoodTypeEntity> foodTypes = foodTypeSysService.find(null, null, 1, 10);
        model.addAttribute("foodTypes", foodTypes);

        //根据ID查询要修改的菜品
        FoodEntity food = foodSysService.findById(Integer.parseInt(id));

        model.addAttribute("food", food);
        //跳转到更新页面
        return "sys/foodUpdate";
    }

    //修改图片
    @RequestMapping("/food/submitUpdate")
    public void updateSubmit(FoodEntity foodEntity, MultipartFile myfile) {
        String path = filePath + File.separator /*+ newName*/;
        File file1 = new File(path);
        try {
            myfile.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (foodEntity.getDiscount() == 0) {
            foodEntity.setDiscount(1);
        }

        //更新菜品，先根据ID找到要修改的菜品
        FoodEntity  food = foodSysService.findById(foodEntity.getId());
        System.out.println("原始菜品："+food);

        //存入图片名
        foodEntity.setImg(path);

        food.setFoodName(foodEntity.getFoodName());
        food.setFoodTypeId(foodEntity.getFoodTypeId());
        food.setDiscount(foodEntity.getDiscount());
        food.setPrice(foodEntity.getPrice());
        food.setRemark(foodEntity.getRemark());
        //保存菜品相关信息到数据库
        foodSysService.update(food);

        //return "redirect:/sys/food/list";
    }

    @RequestMapping("/food/ajaxFoodName")
    public void ajaxFoodName(String foodName, HttpServletResponse response) throws Exception {
        //查询用户输入的菜品名是否已经存在
        FoodEntity food = foodSysService.findByFoodName(foodName);

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        if (food != null) {
            //菜系名字已存在
            out.println("fail");
        } else {
            out.println("success");
        }
    }

}
