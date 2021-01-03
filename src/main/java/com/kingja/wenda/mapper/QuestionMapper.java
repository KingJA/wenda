package com.kingja.wenda.mapper;

import com.kingja.wenda.model.Question;
import com.kingja.wenda.model.QuestionExample;
import tk.mybatis.mapper.common.Mapper;

public interface QuestionMapper extends Mapper<Question> {
    long countByExample(QuestionExample example);
}