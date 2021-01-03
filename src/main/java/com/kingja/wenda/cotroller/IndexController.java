package com.kingja.wenda.cotroller;

import com.kingja.wenda.service.QuestionService;
import com.kingja.wenda.result.PagedData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class IndexController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PagedData pagedData = questionService.getQuestions(keyword,pageNum, pageSize);
        model.addAttribute("pageData", pagedData);
        return "index";
    }
}
