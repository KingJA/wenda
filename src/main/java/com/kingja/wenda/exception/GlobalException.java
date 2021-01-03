package com.kingja.wenda.exception;



import com.kingja.wenda.enums.ResultEnum;

import lombok.Getter;

/**
 * Description：TODO
 * Create Time：2018/1/10 15:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Getter
public class GlobalException extends RuntimeException {
    private Integer code;

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public GlobalException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
