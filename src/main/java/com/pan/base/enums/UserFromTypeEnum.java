package com.pan.base.enums;

/**
 * Created by Pangaofeng on 2018/8/18
 */
public enum UserFromTypeEnum {

    app("1", "客户端"),
    system("2", "后台");

    private String id;
    private String name;

    private UserFromTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getIdByName(String id) {
        for (UserFromTypeEnum t : UserFromTypeEnum.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
