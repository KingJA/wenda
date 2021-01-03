package com.kingja.wenda.mapper;

import com.kingja.wenda.model.Notification;
import com.kingja.wenda.model.NotificationExample;
import tk.mybatis.mapper.common.Mapper;

public interface NotificationMapper extends Mapper<Notification> {
    long countByExample(NotificationExample example);
}