package com.kingja.wenda.vo;

import com.kingja.wenda.model.Question;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 16:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class QuestionVO extends Question {
    private String avatarUrl;

    @Override
    public String toString() {
        return "QuestionVO{" +
                "avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
