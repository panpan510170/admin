package com.pan.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Pangaofeng on 2018/9/11
 */
@Data
public class UserVO {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("token")
    private String token;
}
