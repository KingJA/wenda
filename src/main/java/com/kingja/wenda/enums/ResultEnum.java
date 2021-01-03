package com.kingja.wenda.enums;

import lombok.Getter;

/**
 * Description：TODO
 * Create Time：2018/1/10 15:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public enum ResultEnum {
    /*用户相关*/
    NOT_LOGIN(1001, "用户未登陆"),
    LOGIN_ERROR(1002, "登录失败"),
    /*评论相关*/
    COMMENT_NOT_FOUNT(2201, "找不到评论"),
    COMMENT_TYPE_NOT_EXIST(2202, "评论类型不存在"),
    COMMENT_NOT_EXIST(2203, "评论不存在"),

    /*通知相关*/
    NOTIFICATION_NOT_FOUNT(2301, "找不到通知"),
    /*问题相关*/
    QUESTION_NOT_EXIST(3202, "问题不存在"),
    /*服务器相关*/
    SYSTEM_ERROR(5000, "服务器猫眼了"),
    IMAGE_ERROR(5001, "图片处理异常"),
    ERROR_INSERT_SITE(2001, "添加网址失败");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
