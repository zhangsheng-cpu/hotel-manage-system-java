package com.demo.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 过往云烟~
 */
@Controller
@RequestMapping("/sys")
public class IndexSysController {

    @RequestMapping("/index")
    public String index() {
        return "sys/index";
    }
}
