package com.kingja.wenda.dto;

import com.kingja.wenda.model.Question;
import com.kingja.wenda.model.User;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 16:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class QuestionDTO extends Question {
    private User user;
}
