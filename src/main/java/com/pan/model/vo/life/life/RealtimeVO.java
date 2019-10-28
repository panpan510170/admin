package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 当前时间天气VO
 * @author pan
 * @date 2019/10/28 15:28
 */
@Data
public class RealtimeVO {
    @ApiModelProperty("天气情况，如：晴、多云")
    private String info;

    @ApiModelProperty("天气标识id，和info对应")
    private String wid;

    @ApiModelProperty("温度，可能为空")
    private String temperature;

    @ApiModelProperty("湿度，可能为空")
    private String humidity;

    @ApiModelProperty("风向，可能为空")
    private String direct;

    @ApiModelProperty("风力，可能为空")
    private String power;

    @ApiModelProperty("空气质量指数，可能为空")
    private String aqi;
}
