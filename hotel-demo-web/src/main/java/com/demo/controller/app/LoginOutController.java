package com.demo.controller.app;


import com.demo.controller.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/app")
public class LoginOutController {

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("session_user");
        CookieUtils.removeCookie("loginname_pass",request,response);
        return "app/login";
    }
}
