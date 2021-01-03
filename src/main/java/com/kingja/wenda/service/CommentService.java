package com.kingja.wenda.service;

import com.kingja.wenda.enums.CommentEnum;
import com.kingja.wenda.model.Comment;
import com.kingja.wenda.model.User;
import com.kingja.wenda.vo.CommentVO;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/30 0030 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface CommentService {

    public void insert(Comment comment, User user);

    List<CommentVO> listByTargetId(Integer id, CommentEnum  commentEnum);
}
