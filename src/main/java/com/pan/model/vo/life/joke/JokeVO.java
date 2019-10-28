package com.pan.model.vo.life.joke;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 笑话vo
 * @author pan
 * @date 2019/10/25 18:16
 */
@Data
public class JokeVO {

    @ApiModelProperty("时间戳")
    private long unixtime;
    @ApiModelProperty("hashId")
    private String hashId;
    @ApiModelProperty("内容")
    private String content;
}
