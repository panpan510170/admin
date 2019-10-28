package com.pan.model.vo.life.life;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/10/28 15:57
 */
@Data
public class WidTypeVO {
    @ApiModelProperty("天气id")
    private String wid;
    @ApiModelProperty("天气类型")
    private String weather;
}
