package com.mshd.vo.system;

import com.mshd.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@Data
@ApiModel(value = "userParamVO",description = "系统用户传参视图")
public class UserParamVO extends PageVO {

    @ApiModelProperty("用户名")
    private String userName;
}
