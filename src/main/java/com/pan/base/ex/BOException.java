package com.pan.base.ex;

/**
 * @Description: 业务异常 用做处理程序抛出的异常信息
 * Created by Pangaofeng on 2018/9/5
 */
public class BOException extends RuntimeException{
    private static final long serialVersionUID = -6502746712952833008L;

    private Integer _code;

    public BOException(Integer code, String message) {
        super(message);
        this.set_code(code);
    }

    public Integer get_code() {
        return _code;
    }

    public void set_code(Integer _code) {
        this._code = _code;
    }
}
