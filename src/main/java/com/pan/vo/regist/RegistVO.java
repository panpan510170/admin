package com.pan.vo.regist;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Pangaofeng on 2018/11/8
 */
@Data
public class RegistVO {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String validate1;

    @ApiModelProperty("验证码")
    private String validate2;

    @ApiModelProperty("验证码")
    private String validate3;

    @ApiModelProperty("验证码")
    private String validate5;

}
