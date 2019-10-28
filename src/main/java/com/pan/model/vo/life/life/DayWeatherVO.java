package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 天气情况
 * @author pan
 * @date 2019/10/28 15:34
 */
@Data
public class DayWeatherVO {
    @ApiModelProperty("白天")
    private String day;
    @ApiModelProperty("夜晚")
    private String night;

}
