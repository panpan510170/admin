package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 老黄历入参
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class LaohuangliParamVO {
    @ApiModelProperty("日期，格式2014-09-09")
    private String date;
}
