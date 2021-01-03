package com.kingja.wenda.service;

import com.kingja.wenda.model.Notification;
import com.kingja.wenda.result.PagedData;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2021/1/3 0003 0:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NotificationService {
    PagedData getNotifications(Integer receiverId, Integer pageNum, Integer pageSize);

    int getUnreadCount(Integer userId);

    int setReaded(Notification notification);
}
