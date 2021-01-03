package com.kingja.wenda.cotroller;

import com.kingja.wenda.model.Notification;
import com.kingja.wenda.model.User;
import com.kingja.wenda.service.NotificationService;
import com.kingja.wenda.service.QuestionService;
import com.kingja.wenda.result.PagedData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:TODO
 * Create Time:2020/12/26 0026 23:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class ProfileController {
    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/profile/{section}")
    public String profile(HttpServletRequest request, Model model, @PathVariable(value = "section") String section,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
//
//        int unreadCount = notificationService.getUnreadCount(user.getId());
//        model.addAttribute("unreadCount", unreadCount);

        if ("questions".equals(section)) {
            PagedData pagedData = questionService.getQuestionsByCreator(user.getId(), pageNum, pageSize);
            model.addAttribute("pageData", pagedData);
            model.addAttribute("sectionName", "我的问题");
            model.addAttribute("section", "questions");

        } else if ("replies".equals(section)) {
            PagedData notificationPageData = notificationService.getNotifications(user.getId(), pageNum, pageSize);
            model.addAttribute("pageData", notificationPageData);
            model.addAttribute("sectionName", "最近回复");
            model.addAttribute("section", "replies");
        }
        return "profile";
    }
}
