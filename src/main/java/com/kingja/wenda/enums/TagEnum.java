package com.kingja.wenda.enums;

import lombok.Getter;

/**
 * Description:TODO
 * Create Time:2020/11/27 12:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public enum TagEnum {

    LEVEL_1(1),
    LEVEL_2(2);
    private int type;

    TagEnum(int type) {
        this.type = type;
    }
}
