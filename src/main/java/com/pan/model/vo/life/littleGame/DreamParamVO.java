package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 解梦入参
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class DreamParamVO {
    @ApiModelProperty("梦境关键字，如：黄金 需要utf8 urlencode")
    private String q;
    @ApiModelProperty("指定分类，默认全部")
    private Integer cid;
    @ApiModelProperty("是否显示详细信息，1:是 0:否，默认0")
    private int full;

}
