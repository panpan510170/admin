package com.pan.model.vo.life.lottery;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author pan
 * @date 2019/10/23 18:51
 */
@Data
public class LotteryResultVO {
    @ApiModelProperty("彩票id")
    private String lottery_id;
    @ApiModelProperty("彩票名称")
    private String lottery_name;
    @ApiModelProperty("中奖结果")
    private String lottery_res;
    @ApiModelProperty("期数")
    private String lottery_no;
    @ApiModelProperty("开奖日期")
    private String lottery_date;
    @ApiModelProperty("兑奖截止日期")
    private String lottery_exdate;
    @ApiModelProperty("本期销售额，可能为空")
    private String lottery_sale_amount;
    @ApiModelProperty("奖池滚存，可能为空")
    private String lottery_pool_amount;
    @ApiModelProperty("开奖详情，可能为null")
    private List<LotteryPrizeDetailVO> lottery_prize;
}
