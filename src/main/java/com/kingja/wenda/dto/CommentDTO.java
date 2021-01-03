package com.kingja.wenda.dto;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/30 0030 2:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class CommentDTO {
    private int parentId;
    private String content;
    private int type;
    private int userId;
}
