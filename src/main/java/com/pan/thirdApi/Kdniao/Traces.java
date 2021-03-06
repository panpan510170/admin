package com.pan.thirdApi.Kdniao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Pangaofeng on 2018/9/22
 */
@Data
public class Traces {

    @ApiModelProperty(value = "物流信息")
    private String AcceptStation;

    @ApiModelProperty(value = "时间")
    private String AcceptTime;

    @ApiModelProperty(value = "状态")
    private String status;
}
