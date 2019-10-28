package com.pan.base.enums.life;

/**
 * 小游戏api路径枚举
 * @author pan
 * @date 2019/10/23 18:27
 */
public enum LifeApiUrlEnum {

    weather("天气预报查询","http://apis.juhe.cn/simpleWeather/query"),
    weatherType("天气种类列表","http://apis.juhe.cn/simpleWeather/wids"),
    getCityList("支持城市列表","http://apis.juhe.cn/simpleWeather/cityList");

    private String name;
    private String url;

    LifeApiUrlEnum(String name, String url) {
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
