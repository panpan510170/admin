package com.pan.base.enums.life;

/**
 * 笑话api路径枚举
 * @author pan
 * @date 2019/10/23 18:27
 */
public enum JokeApiUrlEnum {
    randJoke("随机获取笑话","http://v.juhe.cn/joke/randJoke.php"),
    newJoke("最新笑话","http://v.juhe.cn/joke/content/text.php"),
    list("按更新时间查询笑话","http://v.juhe.cn/joke/content/list.php");

    private String name;
    private String url;

    JokeApiUrlEnum(String name, String url) {
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
