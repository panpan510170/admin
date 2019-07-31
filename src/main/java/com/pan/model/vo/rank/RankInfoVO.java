package com.pan.model.vo.rank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/7/8 17:52
 */
@Data
public class RankInfoVO {
    @ApiModelProperty("上一名")
    private RankVO former;

    @ApiModelProperty("自己")
    private RankVO self;

    @ApiModelProperty("下一名")
    private RankVO latter;
}
