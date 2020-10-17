package com.sharding.jdbc.mapper;

import com.sharding.jdbc.po.UserPackRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/13 17:48
 */
@Mapper
public interface UserPackRecordMapper {
    /**
     * 创建用户阅读课程记录
     *
     * @param userPackRecord 实体
     * @return int
     */
    @Insert(" INSERT INTO user_pack_records ( user_id ,pack_id ,start_time ,reading_duration ,start_point ,end_point ,created_at) " +
            " VALUES ( #{userId} ,#{packId} ,#{startTime} ,#{readingDuration} ,#{startPoint} ,#{endPoint} ,NOW()) ")
    int createUserPackRecord(UserPackRecord userPackRecord);

    /**
     * 查找所有用户阅读课程记录
     *
     * @param startTime 开始阅读时间
     * @param endTime   j结束阅读时间
     * @return UserPackRecord 集合
     */
    @Select("SELECT id,user_id,pack_id,start_time,reading_duration,start_point,end_point,created_at FROM user_pack_records " +
            "WHERE start_time>#{startTime} AND start_time<=#{endTime} ORDER BY id DESC ")
    List<UserPackRecord> getUserPackRecordListByTimeRange(@Param("startTime") LocalDateTime startTime,
                                                          @Param("endTime") LocalDateTime endTime);
}
