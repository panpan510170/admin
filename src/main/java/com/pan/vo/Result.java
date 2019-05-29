package com.pan.vo;

import lombok.Data;

@Data
public class Result {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;


    private Integer status;
    private Boolean sessionStatus;
    private String message;
    private Object data;

}
