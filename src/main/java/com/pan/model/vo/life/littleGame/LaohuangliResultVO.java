package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 老黄历入参
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class LaohuangliResultVO {
    @ApiModelProperty("阳历")
    private Date yangli;
    @ApiModelProperty("阴历")
    private String yinli;
    @ApiModelProperty("五行")
    private String wuxing;
    @ApiModelProperty("冲煞")
    private String chongsha;
    @ApiModelProperty("彭祖百忌")
    private String baiji;
    @ApiModelProperty("吉神宜趋")
    private String jishen;
    @ApiModelProperty("宜")
    private String yi;
    @ApiModelProperty("凶神宜忌")
    private String xiongshen;
    @ApiModelProperty("忌")
    private String ji;

    @ApiModelProperty("时间段")
    private String hours;
    @ApiModelProperty("描述")
    private String des;

}
