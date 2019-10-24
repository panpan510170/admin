package com.pan.model.vo.life.lottery;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/10/24 14:46
 */
@Data
public class LotteryPrizeDetailVO {
    @ApiModelProperty("奖项名称")
    private String prize_name;
    @ApiModelProperty("中奖数量，公布数据可能延时可能为空或--")
    private String 	prize_num;
    @ApiModelProperty("中奖金额")
    private String 	prize_amount;
    @ApiModelProperty("中奖条件")
    private String prize_require;

}
