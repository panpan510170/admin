package com.pan.model.vo.rank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/7/10 14:53
 */
@Data
public class RankInfoLogVO {

    @ApiModelProperty("榜单名称")
    private String rankName;

    @ApiModelProperty("轮次")
    private Integer rounds;

    @ApiModelProperty("分组")
    private Integer team;

    @ApiModelProperty("阶段")
    private Integer step;

    @ApiModelProperty("日期")
    private Integer dateKey;

    @ApiModelProperty("来源方")
    private Long source;

    @ApiModelProperty("目标方")
    private Long target;

    @ApiModelProperty("权重累计分值")
    private Long score;

    @ApiModelProperty("关联业务id")
    private Long bizId;

    @ApiModelProperty("二级关联业务id")
    private Long transId;

    @ApiModelProperty("备注")
    private String remark;

}
