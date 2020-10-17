package com.sharding.jdbc.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("User")
public class User {
    /**
     * 自增ID
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 删除标识：0删除，1存活
     */
    private Integer active;
}
