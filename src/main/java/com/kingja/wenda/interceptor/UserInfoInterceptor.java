package com.kingja.wenda.interceptor;

import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.User;
import com.kingja.wenda.model.UserExample;
import com.kingja.wenda.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:TODO
 * Create Time:2020/12/26 0026 1:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

@Service
public class UserInfoInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;
    @Autowired
    NotificationService notificationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器:"+request.getServletPath());
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    User user = userMapper.selectOneByExample(userExample);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        request.getSession().setAttribute("unreadCount",  notificationService.getUnreadCount(user.getId()));
                    }
                    break;
                }
            }
        }
        return true;
    }
}
