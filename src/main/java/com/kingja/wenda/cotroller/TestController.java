package com.kingja.wenda.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:TODO
 * Create Time:2020/12/22 0022 22:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class TestController {

    @RequestMapping("/show")
    public String show(@RequestParam("name") String name, HttpServletRequest request) {
        System.out.println(name);
        request.getSession().setAttribute("name",name+"000");
        return "show";
    }
}
