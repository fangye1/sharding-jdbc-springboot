package com.sharding.jdbc.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/10/13 17:56
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("Pack")
public class Pack {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 课程名称
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 有效标识
     */
    private Integer active;
}
