package com.pan.base.enums.system;

/**
 * @author pan
 * @date 2019/9/10 11:28
 */
public enum SystemUserLogEnum {
    login(1, "登陆"),
    loginOut(1, "登出"),
    no(99, "不知道");

    private int id;
    private String name;

    private SystemUserLogEnum(int id, String name) {
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
        for (SystemUserLogEnum t : SystemUserLogEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
