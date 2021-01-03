package com.kingja.wenda.mapper;

import com.kingja.wenda.model.Comment;
import com.kingja.wenda.model.CommentExample;
import tk.mybatis.mapper.common.Mapper;

public interface CommentMapper extends Mapper<Comment> {
    long countByExample(CommentExample example);
}