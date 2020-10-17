package com.sharding.jdbc.service;

import com.sharding.jdbc.po.User;
import com.github.pagehelper.PageInfo;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
public interface UserService {
    /**
     * 创建用户信息
     *
     * @param user 实体
     * @return User
     */
    User createUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 实体
     * @return true 成功，false 失败
     */
    boolean updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param userId 唯一标识id
     * @return true 成功，false 失败
     */
    boolean deleteUser(long userId);

    /**
     * 按id查询用户信息
     *
     * @param userId 唯一标识id
     * @return User
     */
    User getUserById(long userId);

    /**
     * 分页查询所有用户信息
     *
     * @param pageNum  页码
     * @param pageSize 页数
     * @return User 集合
     */
    PageInfo<User> getUserList(int pageNum, int pageSize);
}
