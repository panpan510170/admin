package com.pan.vo.rank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pan
 * @date 2019/7/8 16:38
 */
@Data
public class RankVO {
    @ApiModelProperty("排行名称")
    private String name;

    @ApiModelProperty("维度值")
    private String dimValue;

    @ApiModelProperty("用户id")
    private long userId;

    @ApiModelProperty("权重值")
    private long weight;

    @ApiModelProperty("排名")
    private long rank;

    @ApiModelProperty("时间戳")
    private long time;

    public RankVO() {
    }

    public RankVO(String name, String dimValue){
        this.name = name;
        this.dimValue = dimValue;
    }

    public RankVO(String name, String dimValue,long time){
        this.name = name;
        this.dimValue = dimValue;
        this.time = time;
    }

    public RankVO(String name, String dimValue, long userId,long time){
        this.name = name;
        this.dimValue = dimValue;
        this.userId=userId;
        this.time = time;

    }
    public RankVO(String name, String dimValue, long userId, long weight,long time) {
        this.name = name;
        this.dimValue = dimValue;
        this.userId=userId;
        this.weight = weight;
        this.time = time;
    }
    public RankVO(String name, String dimValue, long userId, long weight, long rank,long time) {
        this.name = name;
        this.dimValue = dimValue;
        this.userId=userId;
        this.weight = weight;
        this.rank = rank;
        this.time = time;
    }
}
