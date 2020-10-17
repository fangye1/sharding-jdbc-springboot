package com.sharding.jdbc.service.impl;

import com.sharding.jdbc.mapper.UserMapper;
import com.sharding.jdbc.po.User;
import com.sharding.jdbc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        userMapper.createUser(user);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.updateUser(user);
        return result > 0;
    }

    @Override
    public boolean deleteUser(long userId) {
        int result = userMapper.deleteUser(userId);
        return result > 0;
    }

    @Override
    public User getUserById(long userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public PageInfo<User> getUserList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.getUserList();
        return new PageInfo<>(userList);
    }
}
