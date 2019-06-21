package com.pan.vo.rank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 榜单控制VO
 * @author pan
 * @date 2019/6/20 17:47
 */
@Data
public class CoreRankVO{

    @ApiModelProperty("榜单名称")
    private String name;
    @ApiModelProperty("维度类型  1有2没有")
    private int dimensionType;
    @ApiModelProperty("权重")
    private Long basics;
    @ApiModelProperty("清除榜单数据时间:毫秒")
    private Long clearTime;
    @ApiModelProperty("开始使用时间:毫秒")
    private Long startTime;
    @ApiModelProperty("结束使用时间:毫秒")
    private Long endTime;
}
