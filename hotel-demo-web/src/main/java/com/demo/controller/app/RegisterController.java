package com.demo.controller.app;

import com.demo.entity.UserEntity;
import com.demo.service.app.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/app")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register/index")
    public String index() {
        return "app/login";
    }

    @RequestMapping("/register")
    public String register() {

        //进入注册页面
        System.out.println("=============注册===========");
        return "app/register";
    }


    @RequestMapping("/register/ajaxLoginName")
    public void ajaxLoginName(String loginName, HttpServletResponse response) throws IOException {
        //校验登录名是否已经存在
        //根据用户名查询用户
        UserEntity user = userService.findByLoginName(loginName);

        if (user != null) {
            response.setCharacterEncoding("utf-8");
            //当前用户输入的登录名已经存在
            PrintWriter printWriter = response.getWriter();
            printWriter.print("您输入的登录名已存在，请重新输入！");

            //刷新
            printWriter.flush();
            //关闭
            printWriter.close();
        }

    }

    @RequestMapping("/register/submitTable")
    public String submitTable(UserEntity userEntity) {
        //提交注册表单
        System.out.println("====提交注册表单==========");

        //保存用户输入的信息到数据库
        UserEntity user = new UserEntity();

        user.setLoginName(userEntity.getLoginName());
        user.setPhone(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());
        user.setPassWord(userEntity.getPassWord());

        userService.insertUser(user);
        return "redirect:/app/register/index";
    }
}
