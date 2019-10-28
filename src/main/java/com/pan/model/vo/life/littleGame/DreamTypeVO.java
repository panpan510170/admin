package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 梦的类型
 * @author pan
 * @date 2019/10/28 10:38
 */
@Data
public class DreamTypeVO {
    @ApiModelProperty("类别id")
    private Integer id;
    @ApiModelProperty("类别名称")
    private String name;
    @ApiModelProperty("所属父类ID，0为顶级分类")
    private Integer fid;

}
