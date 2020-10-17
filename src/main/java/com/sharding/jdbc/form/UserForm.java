package com.sharding.jdbc.form;

import com.sharding.jdbc.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@ApiModel(value = "UserForm", description = "用户信息")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserForm {
    @ApiModelProperty(value = "用户信息ID")
    private String userId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;

    public User toUser() {
        User user = User.builder()
                .id(this.userId)
                .name(this.name == null ? "" : this.name)
                .age(this.age)
                .build();
        return user;
    }
}
