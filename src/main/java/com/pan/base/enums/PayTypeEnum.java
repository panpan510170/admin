package com.pan.base.enums;

/**
 * 付款类型枚举类
 * @author pan
 * @date 2019/5/29 14:58
 */
public enum PayTypeEnum {
    aliPay(1,"支付宝支付","com.pan.test.designPatterns.factoryAddstrategy.AliPay"),
    wechatPay(2,"微信支付","com.pan.test.designPatterns.factoryAddstrategy.WechatPay"),
    lianlianPay(3,"连连支付",""),
    rongbaoPay(4,"融宝支付","");

    private int id;
    private String name;
    private String className;

    PayTypeEnum(int id, String name,String className) {
        this.id = id;
        this.name = name;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public static PayTypeEnum getPayTypeEnumByName(int id) {
        for (PayTypeEnum t : PayTypeEnum.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
