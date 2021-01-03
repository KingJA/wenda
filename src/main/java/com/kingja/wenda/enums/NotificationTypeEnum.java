package com.kingja.wenda.enums;

import lombok.Getter;

/**
 * Description:TODO
 * Create Time:2021/1/2 0002 23:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public enum NotificationTypeEnum {
    COMMENT_QUESTION(1, "回复了问题"),
    COMMENT_COMMENT(2, "回复了评论");

    private final int type;
    private final String des;

    NotificationTypeEnum(int type, String des) {
        this.type = type;
        this.des = des;
    }

    public static String getTypeDes(int type) {
        for (NotificationTypeEnum value : values()) {
            if (value.type == type) {
                return value.getDes();
            }
        }
        return "";
    }
}
