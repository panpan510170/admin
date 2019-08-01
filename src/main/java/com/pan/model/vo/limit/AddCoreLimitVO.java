package com.pan.model.vo.limit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author pan
 * @date 2019/7/31 10:40
 */
@Data
public class AddCoreLimitVO {
    @ApiModelProperty(value="限制名称",required = true)
    private String name;

    @ApiModelProperty(value="结束时间",required = true)
    private Long endTime;

    @ApiModelProperty(value="备注",required = true)
    private String remark;

    @ApiModelProperty(value="项目名称",required = true)
    private String proName;

    @ApiModelProperty(value="项目内容",required = true)
    private List<LimitItemVO> list;
}
