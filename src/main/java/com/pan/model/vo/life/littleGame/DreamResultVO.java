package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 解梦结果
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class DreamResultVO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("描述")
    private String des;
    @ApiModelProperty("详情")
    private List<String> list;

}
