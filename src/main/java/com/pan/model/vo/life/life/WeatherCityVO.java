package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 天气城市VO
 * @author pan
 * @date 2019/10/28 15:46
 */
@Data
public class WeatherCityVO {
    @ApiModelProperty("城市id")
    private String id;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("区")
    private String district;
}
