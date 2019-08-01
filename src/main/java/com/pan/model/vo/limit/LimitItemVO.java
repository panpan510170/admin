package com.pan.model.vo.limit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/7/31 18:02
 */
@Data
public class LimitItemVO {

    @ApiModelProperty(value="维度标识",required = true)
    private String dimension;

    @ApiModelProperty(value="限制类型1固定(计算限制次数即可),2持续(计算限制次数*时间)",required = true)
    private Integer type;

    @ApiModelProperty(value="限制次数",required = true)
    private Integer number;

    @ApiModelProperty(value="时间",required = true)
    private long time;

    @ApiModelProperty(value="时间单位",required = true)
    private String unit;

    @ApiModelProperty(value="优先级",required = true)
    private int priority;
}
