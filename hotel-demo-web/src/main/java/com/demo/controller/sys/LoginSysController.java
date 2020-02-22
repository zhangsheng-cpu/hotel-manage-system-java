package com.demo.controller.sys;

import com.demo.controller.util.CookieUtils;
import com.demo.entity.UserEntity;
import com.demo.service.sys.UserSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/sys")
public class LoginSysController {

    @Autowired
    private UserSysService userSysService;

    public static final String SESSION_NAME = "session_user";

    @RequestMapping("/login/index")
    public String loginIndex() {
        return "sys/index";
    }

    @RequestMapping("/login")
    public String login(String loginname, String password, String remember, HttpSession session,
                        HttpServletResponse response, HttpServletRequest request,
                        Model model) throws UnsupportedEncodingException {

        //提交登录表单  去数据库查询用户输入的登录名和密码是否存在
        if(loginname != null &&password != null) {

            UserEntity  user = userSysService.findByLoginNameAndPass(loginname,password);

            if(user != null) {

                if(remember != null && remember.equals("reme")) {
                    //记住账户一周   需传递cookie的名字，要保存的用户名和密码，有效时间（毫秒）
                    CookieUtils.addCookie(URLEncoder.encode(loginname, "utf-8"),
                            URLEncoder.encode(password, "utf-8"),7*24*60*1000,
                            "loginname_pass", response, request);
                }
                //session默认半个小时在整个项目中有效
                session.setAttribute(SESSION_NAME, user);
                //登录成功。跳转到首页
                return "redirect:/sys/login/index";
            }else {
                model.addAttribute("message", "用户名或密码错误，请重新输入！");
                return "sys/login";
            }
        }
        //跳转到登录页面
        return "sys/login";
    }
}
