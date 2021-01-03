package com.kingja.wenda.mapper;

import com.kingja.wenda.model.Tag;
import com.kingja.wenda.model.TagExample;
import tk.mybatis.mapper.common.Mapper;

public interface TagMapper extends Mapper<Tag> {
    long countByExample(TagExample example);
}