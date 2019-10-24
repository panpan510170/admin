package com.pan.base.enums.life;

/**
 * 彩票api路径枚举
 * @author pan
 * @date 2019/10/23 18:27
 */
public enum  LotteryApiUrlEnum {
    selectType("支持彩种列表","http://apis.juhe.cn/lottery/types"),
    selectResult("开奖结果查询","http://apis.juhe.cn/lottery/query"),
    selectResultHistory("历史开奖结果查询","http://apis.juhe.cn/lottery/history"),
    bonus("中奖计算器","http://apis.juhe.cn/lottery/bonus");

    private String name;
    private String url;

    LotteryApiUrlEnum(String name,String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
