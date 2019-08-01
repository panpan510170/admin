package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "s_user")
@org.hibernate.annotations.Table(appliesTo = "s_user",comment="系统用户表")
@Data
public class SUser extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '用户名称'")
    private String userName;

    @Column(name = "password",nullable = false,columnDefinition = "varchar(100) comment '密码'")
    private String password;

    @Column(name = "phone",nullable = false,unique = true,columnDefinition = "varchar(30) comment '手机号'")
    private String phone;

    @Column(name = "status",nullable = false,columnDefinition = "int comment '状态(1-正常,2-注销,3-停用)'")
    private Integer status;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Transient
    private String roleName;

    @Transient
    private String descrition;
}