package com.kingja.wenda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingja.wenda.dto.QuestionDTO;
import com.kingja.wenda.mapper.QuestionMapper;
import com.kingja.wenda.mapper.QuestionMapperCus;
import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.Question;
import com.kingja.wenda.model.QuestionExample;
import com.kingja.wenda.model.User;
import com.kingja.wenda.service.QuestionService;
import com.kingja.wenda.utils.PageUtil;
import com.kingja.wenda.result.PagedData;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 21:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapperCus questionMapperCus;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public PagedData getQuestions(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (!StringUtils.isBlank(keyword)) {
            String[] keywords = StringUtils.split(keyword, " ");
            keyword = Arrays.stream(keywords).collect(Collectors.joining("|"));
        }
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.setOrderByClause("gmt_create desc");
//        List<Question> questions = questionMapper.selectByExample(questionExample);

        List<Question> questions = questionMapperCus.searchQuestions(keyword);
        return getPagedData(questions);
    }


    @Override
    public PagedData getQuestionsByCreator(int id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExample(questionExample);
        return getPagedData(questions);
    }

    private PagedData getPagedData(List<Question> questions) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return PageUtil.getPagedData(new PageInfo<>(questions), questionDTOList);
    }

    @Override
    public QuestionDTO getQuestionDetail(int id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            questionMapper.insertSelective(question);
        } else {
            question.setGmtModified(null);
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }

    @Override
    public int incViewCount(int id) {
        return questionMapperCus.incViewCount(id);
    }

    /**
     * 获取相关问题列表
     *
     * @param questionDTO
     * @return
     */
    @Override
    public List<Question> getRelatedQuestions(QuestionDTO questionDTO) {
        if (StringUtils.isBlank(questionDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexpTag);
        return questionMapperCus.getRelatedQuestions(question);

    }
}
