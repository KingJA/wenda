package com.kingja.wenda.cotroller;

import com.kingja.wenda.dto.QuestionDTO;
import com.kingja.wenda.enums.CommentEnum;
import com.kingja.wenda.model.Question;
import com.kingja.wenda.service.CommentService;
import com.kingja.wenda.service.QuestionService;
import com.kingja.wenda.vo.CommentVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/27 0027 2:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @Autowired
    CommentService commentService;


    @GetMapping("question/{id}")
    public String question(@PathVariable("id") Integer id, Model model) {
        QuestionDTO questionDetail = questionService.getQuestionDetail(id);
        List<Question> relatedQuestions = questionService.getRelatedQuestions(questionDetail);
        int result = questionService.incViewCount(id);
        List<CommentVO> comments = commentService.listByTargetId(id, CommentEnum.QUESTION);
        model.addAttribute("question", questionDetail);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
