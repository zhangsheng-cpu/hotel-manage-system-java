package com.demo.controller.sys;

import com.demo.controller.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/sys")
public class LoginOutSysController {

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletResponse response, HttpServletRequest request) {

        request.getSession().removeAttribute("session_user");
        CookieUtils.removeCookie("loginname_pass",request,response);
        return "sys/login";
    }
}
