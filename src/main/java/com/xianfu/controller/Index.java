package com.xianfu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Created by xianfuWang
 * @version 2018/4/12.
 */
@Controller
public class Index {

    @RequestMapping(value = {"/index","/"})
    public String index(Model model){
        model.addAttribute("fromUserName","hello");
        return "/hello";
    }
}
