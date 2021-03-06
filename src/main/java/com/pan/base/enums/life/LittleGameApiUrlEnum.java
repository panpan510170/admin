package com.pan.base.enums.life;

/**
 * 小游戏api路径枚举
 * @author pan
 * @date 2019/10/23 18:27
 */
public enum LittleGameApiUrlEnum {

    qqResult("QQ号码测吉凶","http://japi.juhe.cn/qqevaluate/qq"),
    category("周公解梦--梦境类型查询","http://v.juhe.cn/dream/category"),
    dreamQuery("周公解梦--梦境类型查询","http://v.juhe.cn/dream/query"),
    laohuangliD("老黄历--日期","http://v.juhe.cn/laohuangli/d"),
    laohuangliH("老黄历--时辰","http://v.juhe.cn/laohuangli/h");

    private String name;
    private String url;

    LittleGameApiUrlEnum(String name, String url) {
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
