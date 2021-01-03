package com.kingja.wenda.service.impl;

import com.kingja.wenda.enums.CommentEnum;
import com.kingja.wenda.enums.NotificationTypeEnum;
import com.kingja.wenda.enums.ResultEnum;
import com.kingja.wenda.exception.GlobalException;
import com.kingja.wenda.mapper.CommentMapper;
import com.kingja.wenda.mapper.CommentMapperCus;
import com.kingja.wenda.mapper.NotificationMapper;
import com.kingja.wenda.mapper.QuestionMapper;
import com.kingja.wenda.mapper.QuestionMapperCus;
import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.Comment;
import com.kingja.wenda.model.CommentExample;
import com.kingja.wenda.model.Notification;
import com.kingja.wenda.model.Question;
import com.kingja.wenda.model.User;
import com.kingja.wenda.model.UserExample;
import com.kingja.wenda.service.CommentService;
import com.kingja.wenda.vo.CommentVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:TODO
 * Create Time:2020/12/30 0030 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commenntMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionMapperCus questionMapperCus;
    @Autowired
    CommentMapperCus commentMapperCus;

    @Autowired
    UserMapper userMapper;

    @Autowired
    NotificationMapper notificationMapper;


    @Transactional
    @Override
    public void insert(Comment comment, User user) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new GlobalException(ResultEnum.COMMENT_NOT_FOUNT);
        }

        if (comment.getType() == null || !CommentEnum.isExist(comment.getType())) {
            throw new GlobalException(ResultEnum.COMMENT_TYPE_NOT_EXIST);
        }

        if (comment.getType() == CommentEnum.QUESTION.getType()) {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new GlobalException(ResultEnum.QUESTION_NOT_EXIST);
            }
            commenntMapper.insertSelective(comment);
            questionMapperCus.incCommentCount(comment.getParentId());
            createNotify(comment, question.getId(), question.getCreator(), question.getTitle(),
                    user.getName(), NotificationTypeEnum.COMMENT_QUESTION);
        } else {
            Comment dbComment = commenntMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new GlobalException(ResultEnum.COMMENT_NOT_EXIST);
            }
            commenntMapper.insertSelective(comment);
            commentMapperCus.incCommentNum(comment.getParentId());
            createNotify(comment, dbComment.getParentId(), dbComment.getUserId(), dbComment.getContent(),user.getName(),
                    NotificationTypeEnum.COMMENT_COMMENT);
        }
    }

    private void createNotify(Comment comment, Integer questionId, Integer receiverId, String targetTitle,
                              String senderName, NotificationTypeEnum commentQuestion) {
        Notification notification = new Notification();
        notification.setSenderId(comment.getUserId());
        notification.setReceiverId(receiverId);
        notification.setSenderName(senderName);
        notification.setOuterId(questionId);
        notification.setTargetId(comment.getParentId());
        notification.setTargetTitle(targetTitle);
        notification.setType(commentQuestion.getType());
        notificationMapper.insertSelective(notification);
    }

    @Override
    public List<CommentVO> listByTargetId(Integer id, CommentEnum commentEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_time desc");
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(commentEnum.getType());
        List<Comment> comments = commenntMapper.selectByExample(commentExample);

        if (comments.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Integer> userIdSet = comments.stream().map(Comment::getUserId).collect(Collectors.toSet());

        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(userIdSet);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentVO> commentVOS = comments.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            commentVO.setUser(userMap.get(comment.getUserId()));
            return commentVO;
        }).collect(Collectors.toList());

        return commentVOS;
    }
}
