package com.pan.model.entitys.system;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_user_token")
@org.hibernate.annotations.Table(appliesTo = "t_user_token",comment="用户token表")
@Data
public class TUserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false,columnDefinition = "bigint comment '用户id'")
    private Long userId;

    @Column(name = "token",nullable = false,columnDefinition = "varchar(2000) comment 'token'")
    private String token;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}