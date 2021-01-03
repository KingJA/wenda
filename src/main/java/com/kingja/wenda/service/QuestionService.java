package com.kingja.wenda.service;

import com.kingja.wenda.dto.QuestionDTO;
import com.kingja.wenda.model.Question;
import com.kingja.wenda.result.PagedData;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 21:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface QuestionService {

    PagedData getQuestions(String keyword, int pageNum, int pageSize);

    PagedData getQuestionsByCreator(int id, int pageNum, int pageSize);

    QuestionDTO getQuestionDetail(int id);

    void createOrUpdate(Question question);
    int incViewCount(int id);

    List<Question> getRelatedQuestions(QuestionDTO questionDTO);
}
