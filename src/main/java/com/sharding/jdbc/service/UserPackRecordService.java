package com.sharding.jdbc.service;

import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.po.UserPackRecord;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
public interface UserPackRecordService {
    /**
     * 创建用户阅读课程记录
     *
     * @param userPackRecord 实体
     * @return UserPackRecord
     */
    UserPackRecord createUserPackRecord(UserPackRecord userPackRecord);

    /**
     * 分页查询所有用户阅读课程记录
     *
     * @param startTime 阅读开始时间
     * @param pageNum   页码
     * @param pageSize  页数
     * @return UserPackRecord 集合
     */
    PageInfo<UserPackRecord> getUserPackRecordList(LocalDateTime startTime, LocalDateTime endTime, int pageNum, int pageSize);
}
