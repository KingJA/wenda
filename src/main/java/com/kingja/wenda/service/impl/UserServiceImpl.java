package com.kingja.wenda.service.impl;

import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.User;
import com.kingja.wenda.model.UserExample;
import com.kingja.wenda.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/27 0027 2:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void createOrUpdateUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        User dbUser = userMapper.selectOneByExample(userExample);
        if (dbUser == null) {
            userMapper.insertSelective(user);
        } else {
//            BeanUtils.copyProperties(user, dbUser);//会赋值null值，不使用
            dbUser.setBio(user.getBio());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(null);
            userMapper.updateByPrimaryKeySelective(dbUser);
        }
    }
}
