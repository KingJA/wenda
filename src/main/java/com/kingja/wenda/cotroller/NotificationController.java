package com.kingja.wenda.cotroller;

import com.kingja.wenda.enums.NotificationTypeEnum;
import com.kingja.wenda.enums.ResultEnum;
import com.kingja.wenda.exception.GlobalException;
import com.kingja.wenda.mapper.NotificationMapper;
import com.kingja.wenda.model.Notification;
import com.kingja.wenda.model.User;
import com.kingja.wenda.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:TODO
 * Create Time:2021/1/3 0003 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class NotificationController {

    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    NotificationService notificationService;


    @GetMapping("/notification/{id}")
    public String openNotification(@PathVariable("id") int notificationId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        Notification notification = notificationMapper.selectByPrimaryKey(notificationId);
        if (notification == null) {
            throw new GlobalException(ResultEnum.NOTIFICATION_NOT_FOUNT);
        }

        int updateCount = notificationService.setReaded(notification);
        if (updateCount != 1) {
            throw new GlobalException(ResultEnum.NOTIFICATION_NOT_FOUNT);
        }
        if (NotificationTypeEnum.COMMENT_COMMENT.getType() == notification.getType()
                || NotificationTypeEnum.COMMENT_QUESTION.getType() == notification.getType()) {
            return "redirect:/question/" + notification.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
