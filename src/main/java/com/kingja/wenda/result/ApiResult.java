package com.kingja.wenda.result;

import com.kingja.wenda.enums.ResultEnum;
import com.kingja.wenda.exception.GlobalException;

import lombok.Getter;

@Getter
public class ApiResult {

    private int code;
    private String msg;
    private Object data;

    public static  ApiResult success(Object data) {
        return new ApiResult(0, "操作成功", data);
    }
    public static  ApiResult successMsg() {
        return new ApiResult(0, "操作成功");
    }
    public static  ApiResult successMsg(String message) {
        return new ApiResult(0, message);
    }

    public static  ApiResult error(ResultEnum codeMsg) {
        return new ApiResult(codeMsg);
    }

    public static  ApiResult error(GlobalException e) {
        return new ApiResult(e.getCode(),e.getMessage());
    }


    public static  ApiResult error(int code, String msg) {
        return new ApiResult(code, msg);
    }

    private ApiResult(Object data) {
        this.data = data;
    }

    private ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ApiResult(ResultEnum codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
