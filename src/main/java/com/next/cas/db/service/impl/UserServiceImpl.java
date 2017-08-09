package com.next.cas.db.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.next.cas.db.mapper.UserMapper;
import com.next.cas.db.model.User;
import com.next.cas.db.model.UserExample;
import com.next.cas.db.service.UserService;

public class UserServiceImpl implements UserService
{
    @Resource
    private UserMapper userMapper;

    @Override
    public User query(String username)
    {
        UserExample example = new UserExample();
        example.createCriteria().andLoginnameEqualTo(username);
        System.out.println(userMapper);
        List<User> list = userMapper.selectByExample(example);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    public void setUserMapper(UserMapper userMapper)
    {
        this.userMapper = userMapper;
    }

}
