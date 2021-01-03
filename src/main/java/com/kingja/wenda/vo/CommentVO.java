package com.kingja.wenda.vo;

import com.kingja.wenda.model.Comment;
import com.kingja.wenda.model.User;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/30 0030 19:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class CommentVO extends Comment {
    private User user;
}
