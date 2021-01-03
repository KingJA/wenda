package com.kingja.wenda.mapper;

import com.kingja.wenda.model.User;
import com.kingja.wenda.model.UserExample;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);
}