package com.mshd.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Pangaofeng on 2018/9/3
 */
@ApiModel(value = "userParamVO",description = "用户传参视图")
public class UserParamVO implements Serializable {

    private static final long serialVersionUID = -7849915522864343401L;

    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别")
    private Integer sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserParamVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
