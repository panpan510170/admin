package com.pan.model.vo.limit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/8/1 9:49
 */
@Data
public class LimitInfoVO {

    @ApiModelProperty(value="限制id",required = true)
    private Long limitId;

    @ApiModelProperty(value="限制维度",required = true)
    private String dimension;

    @ApiModelProperty(value="限制次数",required = true)
    private Integer number;

    @ApiModelProperty(value="限制总次数")
    private Integer totalNumber;
}
