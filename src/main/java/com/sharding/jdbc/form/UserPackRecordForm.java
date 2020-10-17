package com.sharding.jdbc.form;

import com.sharding.jdbc.po.UserPackRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/10/13 17:48
 */
@ApiModel(value = "UserPackRecordForm", description = "用户阅读课程记录")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserPackRecordForm {
    @NotBlank(message = "用户Id不能为空")
    @ApiModelProperty(value = "用户Id")
    private String userId;
    @NotBlank(message = "课程Id不能为空")
    @ApiModelProperty(value = "课程Id")
    private String packId;
    @NotNull(message = "阅读开始时间为空")
    @ApiModelProperty(value = "阅读开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "阅读时长")
    private int readingDuration;
    @ApiModelProperty(value = "开始阅读时间点")
    private int startPoint;
    @ApiModelProperty(value = "结束阅读时间点")
    private int endPoint;

    public UserPackRecord toUserPackRecord() {
        UserPackRecord userPackRecord = UserPackRecord.builder()
                .userId(this.userId)
                .packId(this.packId)
                .startTime(this.startTime)
                .readingDuration(this.readingDuration)
                .startPoint(this.startPoint)
                .endPoint(this.endPoint)
                .build();
        return userPackRecord;
    }
}
