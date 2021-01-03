package com.kingja.wenda.enums;

import lombok.Getter;

/**
 * Description:TODO
 * Create Time:2020/11/27 12:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public enum CommentEnum {

    QUESTION(1),
    COMMENT(2);
    private int type;

    CommentEnum(int type) {
        this.type = type;
    }


    public static boolean isExist(Integer type) {
        for (CommentEnum commentEnum : CommentEnum.values()) {
            if (commentEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
