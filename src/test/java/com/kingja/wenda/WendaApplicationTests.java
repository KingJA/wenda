package com.kingja.wenda;

import com.kingja.wenda.mapper.QuestionMapper;
import com.kingja.wenda.model.Question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class WendaApplicationTests {

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void contextLoads() {
    }
    @Test
    void insertQuestion() {
        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            question.setCreator(3);
            question.setTitle("标题标题标题标题"+i);
            question.setTag("标签"+i);
            question.setDescription("问题详情问题详情问题详情问题详情问题详情"+i);
            question.setCommentCount(new Random().nextInt(100));
            question.setLikeCount(new Random().nextInt(100));
            question.setViewCount(new Random().nextInt(100));
            questionMapper.insertSelective(question);
        }


    }

}
