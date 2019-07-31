package com.pan.model.vo.rank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/7/8 16:37
 */
@Data
public class RankAddResultVO {
    @ApiModelProperty("更新前的排名信息")
    private RankVO before;
    @ApiModelProperty("更新后的排名信息")
    private RankVO after;
}
