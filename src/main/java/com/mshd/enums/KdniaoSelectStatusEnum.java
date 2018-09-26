package com.mshd.enums;

/**
 * 快递鸟第三方物流信息查询接口   状态
 *
 * Created by Pangaofeng on 2018/8/18
 */
public enum KdniaoSelectStatusEnum {

    noTrack(0, "无轨迹"),
    hasLanShou(1, "已揽收"),
    onTheWay(2, "在途中"),
    signAfterReceiving(3, "签收"),
    problemA(4, "问题件");

    private int id;
    private String name;

    private KdniaoSelectStatusEnum(int id, String name) {
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
        for (KdniaoSelectStatusEnum t : KdniaoSelectStatusEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
