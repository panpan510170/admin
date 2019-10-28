package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 天气预报结果
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class WeatherResultVO {
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("当前时间的天气")
    private RealtimeVO realtime;
    @ApiModelProperty("近5天天气情况")
    private List<FutureDayVO> future;

}
