package com.sharding.jdbc.form;

import com.sharding.jdbc.po.Pack;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author qizenan
 * @date 2020/10/13 17:56
 */
@ApiModel(value = "PackForm", description = "课程信息")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PackForm {
    @ApiModelProperty(value = "课程信息ID")
    private String packId;
    @NotBlank(message = "课程名称不能为空")
    @Size(max = 50, message = "课程名称过长")
    @ApiModelProperty(value = "课程名称")
    private String title;
    @ApiModelProperty(value = "摘要")
    private String summary;
    @ApiModelProperty(value = "价格")
    private int price;

    public Pack toPack() {
        Pack pack = Pack.builder()
                .id(this.packId)
                .title(this.title == null ? "" : this.title)
                .summary(this.summary == null ? "" : this.summary)
                .price(this.price)
                .build();
        return pack;
    }

}
