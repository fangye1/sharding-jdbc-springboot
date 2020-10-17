package com.sharding.jdbc.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/10/13 17:48
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("UserPackRecord")
public class UserPackRecord {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 课程Id
     */
    private String packId;
    /**
     * 阅读开始时间
     */
    private LocalDateTime startTime;
    /**
     * 阅读时长
     */
    private Integer readingDuration;
    /**
     * 开始阅读时间点
     */
    private Integer startPoint;
    /**
     * 结束阅读时间点
     */
    private Integer endPoint;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
