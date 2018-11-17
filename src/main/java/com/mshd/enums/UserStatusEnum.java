package com.mshd.enums;

/**
 * Created by Pangaofeng on 2018/8/18
 */
public enum UserStatusEnum {

    normal(1, "正常"),
    cancellation(2, "注销"),
    freeze(3, "停用");

    private int id;
    private String name;

    private UserStatusEnum(int id, String name) {
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
        for (UserStatusEnum t : UserStatusEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
