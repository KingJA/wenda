package com.kingja.wenda.service.impl;

import com.github.pagehelper.PageHelper;
import com.kingja.wenda.enums.NotificationTypeEnum;
import com.kingja.wenda.mapper.NotificationMapper;
import com.kingja.wenda.model.Notification;
import com.kingja.wenda.model.NotificationExample;
import com.kingja.wenda.result.PagedData;
import com.kingja.wenda.service.NotificationService;
import com.kingja.wenda.utils.PageUtil;
import com.kingja.wenda.vo.NotificationVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:TODO
 * Create Time:2021/1/3 0003 0:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public PagedData getNotifications(Integer receiverId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.setOrderByClause("create_time desc");
        notificationExample.createCriteria()
                .andReceiverIdEqualTo(receiverId);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        List<NotificationVO> notificationVOs = notifications.stream().map(notification -> {
            NotificationVO notificationVO = new NotificationVO();
            BeanUtils.copyProperties(notification, notificationVO);
            notificationVO.setTypeDes(NotificationTypeEnum.getTypeDes(notification.getType()));
            return notificationVO;
        }).collect(Collectors.toList());
        return PageUtil.getPagedData(notificationVOs);
    }

    @Override
    public int getUnreadCount(Integer userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverIdEqualTo(userId)
                .andStatusEqualTo(false);
        return notificationMapper.selectCountByExample(notificationExample);
    }

    @Override
    public int setReaded(Notification notification) {
        Notification writeNotification = new Notification();
        writeNotification.setId(notification.getId());
        writeNotification.setStatus(true);
        return notificationMapper.updateByPrimaryKeySelective(writeNotification);
    }
}
