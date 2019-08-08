package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "用户对象")
@Entity
@Table(name = "t_user")
@org.hibernate.annotations.Table(appliesTo = "t_user",comment="系统用户角色表")
@Data
public class TUser extends BaseDO implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    @ApiModelProperty(value = "主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @Column(name = "user_name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '用户名称'")
    private String userName;

    @Column(name = "password",nullable = false,columnDefinition = "varchar(100) comment '密码'")
    private String password;

    @Column(name = "real_name",nullable = false,columnDefinition = "varchar(100) comment '真实姓名'")
    private String realName;

    @Column(name = "id_no",nullable = false,columnDefinition = "varchar(100) comment '身份证号'")
    private String idNo;

    @Column(name = "phone",nullable = false,columnDefinition = "varchar(100) comment '手机号'")
    private String phone;

    @Column(name = "email",nullable = false,columnDefinition = "varchar(100) comment '电子邮件'")
    private String email;

    @Column(name = "sex",nullable = false,columnDefinition = "int(2) comment '性别 1男2女'")
    private Integer sex;

    @Column(name = "status",nullable = false,columnDefinition = "int comment '状态(1-正常,2-注销,3-停用)'")
    private Integer status;

    @Column(name = "reg_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date regTime;
}