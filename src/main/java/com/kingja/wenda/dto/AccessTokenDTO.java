package com.kingja.wenda.dto;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/22 0022 23:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
