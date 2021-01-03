package com.kingja.wenda.mapper;

import org.apache.ibatis.annotations.Param;

public interface CommentMapperCus {
    void incCommentNum(@Param("id")Integer id);
}