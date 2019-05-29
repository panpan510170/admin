package com.pan.model;

import java.io.Serializable;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public class User implements Serializable {
    private static final long serialVersionUID = -1537089391257382347L;

    private Long id;

    private String userNo;

    private String nickName;

    private String userName;

    private String passWord;

    private Integer sex;//1男性 2女性

    private Integer age;

    private String realName;

    private String idNo;

    private String phone;

    private Integer isFirst;

    private Integer source;

    private Integer type;//1普通用户 2会员用户

    private Integer status;//1正常  2冻结  3注销

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNo='" + userNo + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", realName='" + realName + '\'' +
                ", idNo='" + idNo + '\'' +
                ", phone='" + phone + '\'' +
                ", isFirst=" + isFirst +
                ", source=" + source +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
