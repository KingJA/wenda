package com.kingja.wenda.threepart.qiniuyun;

import lombok.Getter;

/**
 * Description:TODO
 * Create Time:2020/11/27 12:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public enum QiniuResultEnum {

    SUCCESS(1,"上传成功"),
    ERROR(0,"上传失败");
    private final int code;
    private final String message;

    QiniuResultEnum(int code,String message) {
        this.code = code;
        this.message = message;
    }
}
