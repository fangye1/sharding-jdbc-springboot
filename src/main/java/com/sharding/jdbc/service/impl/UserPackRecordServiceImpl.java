package com.sharding.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.mapper.UserPackRecordMapper;
import com.sharding.jdbc.po.UserPackRecord;
import com.sharding.jdbc.service.UserPackRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Service
public class UserPackRecordServiceImpl implements UserPackRecordService {
    @Resource
    private UserPackRecordMapper userPackRecordMapper;

    @Override
    public UserPackRecord createUserPackRecord(UserPackRecord userPackRecord) {
        userPackRecordMapper.createUserPackRecord(userPackRecord);
        return userPackRecord;
    }

    @Override
    public PageInfo<UserPackRecord> getUserPackRecordList(LocalDateTime startTime, LocalDateTime endTime, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserPackRecord> userPackRecordList = userPackRecordMapper.getUserPackRecordListByTimeRange(startTime, endTime);
        return new PageInfo<>(userPackRecordList);
    }
}
