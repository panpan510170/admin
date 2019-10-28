package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/10/28 15:32
 */
@Data
public class FutureDayVO {
    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("温度，最低温/最高温")
    private String temperature;

    @ApiModelProperty("天气情况")
    private String weather;

    @ApiModelProperty("风向")
    private String direct;

    @ApiModelProperty("天气情况")
    private DayWeatherVO wid;
}
