package com.sharding.jdbc.mapper;

import com.sharding.jdbc.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Mapper
public interface UserMapper {
    /**
     * 创建用户信息
     *
     * @param user 实体
     * @return int
     */
    @Insert(" INSERT INTO users (name ,age ,active) " +
            " VALUES (#{name} ,#{age} ,1) ")
    int createUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 实体
     * @return int
     */
    @Update(" UPDATE users SET name=#{name},age=#{age} WHERE id=#{id} ")
    int updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE users SET active=0 WHERE id=#{id}")
    int deleteUser(@Param("id") long id);

    /**
     * 按id查找用户信息
     *
     * @param id 唯一标识id
     * @return User
     */
    @Select("SELECT id,name,age,active FROM users WHERE id=#{id} LIMIT 1 ")
    User getUserById(@Param("id") long id);

    /**
     * 查找所有用户信息
     *
     * @return User 集合
     */
    @Select("SELECT id,name,age,active FROM users WHERE active=1 ORDER BY id DESC ")
    List<User> getUserList();
}
