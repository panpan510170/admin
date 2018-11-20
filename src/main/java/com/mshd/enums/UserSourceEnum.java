package com.mshd.enums;

/**
 * Created by Pangaofeng on 2018/8/18
 */
public enum UserSourceEnum {

    front(1, "前端"),
    behind(2, "后台"),
    android(3, "安卓"),
    ios(4, "苹果"),
    wx(5, "微信小程序");

    private int id;
    private String name;

    private UserSourceEnum(int id, String name) {
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
        for (UserSourceEnum t : UserSourceEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
