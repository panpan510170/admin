package com.pan.vo;

import java.io.Serializable;

/**
 * Created by Pangaofeng on 2018/08/23.
 */
public class ResultVO<T> implements Serializable {


    public static Integer CODE_SUCCESS = 1;
    public static Integer CODE_FAILURE = 0;

    private int code;
    private String message;
    private T date;


    public ResultVO() {
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }


    public boolean isSuccess(){
        return this.code== ResultVO.CODE_SUCCESS?true:false;
    }
}
