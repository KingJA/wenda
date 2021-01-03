package com.kingja.wenda.vo;

import com.kingja.wenda.model.Tag;

import java.util.List;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2021/1/1 0001 15:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class TagVO extends Tag {
    private List<Tag> subTags;
}
