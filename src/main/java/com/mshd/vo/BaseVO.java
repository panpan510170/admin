package com.mshd.vo;

import com.mshd.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class BaseVO {

    @ApiModelProperty("通用返回状态编码")
    private Integer code;
    @ApiModelProperty("通用返回信息")
    private String message;

    public void success(ResultCodeEnum codeTypeEnum){
        this.setCode(codeTypeEnum.getId());
        this.setMessage(codeTypeEnum.getName());
    }

    public void error(ResultCodeEnum codeTypeEnum){
        this.setCode(codeTypeEnum.getId());
        this.setMessage(codeTypeEnum.getName());
    }

    public void error(Integer code,String message){
        this.setCode(code);
        this.setMessage(message);
    }

}
