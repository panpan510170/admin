package com.pan.model.vo.life.littleGame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * QQ号码测吉凶 结果
 * @author pan
 * @date 2019/10/25 18:54
 */
@Data
public class QQResultVO {
    @ApiModelProperty("QQ号码")
    private String qqCode;
    @ApiModelProperty("QQ号码测试结论")
    private String conclusion;
    @ApiModelProperty("结论分析")
    private String analysis;
}
