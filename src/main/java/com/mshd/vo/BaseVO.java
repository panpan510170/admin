package com.mshd.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mshd.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {

    @ApiModelProperty("通用返回状态编码")
    private Integer code;
    @ApiModelProperty("通用返回信息")
    private String message;

    public BaseVO(ResultCodeEnum resultCodeEnum){
        this.setCode(resultCodeEnum.getId());
        this.setMessage(resultCodeEnum.getName());
    }

    public BaseVO(Integer code,String message){
        this.setCode(code);
        this.setMessage(message);
    }

    public BaseVO(){
        this.setCode(ResultCodeEnum.success.getId());
        this.setMessage(ResultCodeEnum.success.getName());
    }

    public void success(){
        this.setCode(ResultCodeEnum.success.getId());
        this.setMessage(ResultCodeEnum.success.getName());
    }

    public void error(){
        this.setCode(ResultCodeEnum.performError.getId());
        this.setMessage(ResultCodeEnum.performError.getName());
    }

}
