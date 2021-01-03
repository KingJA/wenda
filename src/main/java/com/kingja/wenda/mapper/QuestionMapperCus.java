package com.kingja.wenda.mapper;

import com.kingja.wenda.model.Question;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapperCus {

    int incViewCount(@Param("id") int id);

    void incCommentCount(@Param("id") Integer id);

    List<Question> getRelatedQuestions(Question question);

    List<Question> searchQuestions(@Param("keyword")String keyword);
}