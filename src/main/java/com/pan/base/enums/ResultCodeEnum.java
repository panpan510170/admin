package com.pan.base.enums;

/**
 * Created by Pangaofeng on 2018/8/18
 */
public enum ResultCodeEnum {

    success(1, "成功"),
    error(2, "失败,请联系技术"),
    bussinessError(3, "业务失败"),
    performError(4, "服务程序异常,请联系技术"),
    paramError(5, "参数错误"),
    loginError(6, "用户未登陆"),
    sessionExpire(7, "Headers请求参数错误"),
    IOError(8, "IO异常,请联系技术"),
    tokenError(9, "登录认证失效,请重新登录");

    private int id;
    private String name;

    private ResultCodeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getIdByName(int id) {
        for (ResultCodeEnum t : ResultCodeEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
