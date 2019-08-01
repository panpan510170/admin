package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "s_role")
@org.hibernate.annotations.Table(appliesTo = "s_role",comment="系统角色表")
@Data
public class SRole extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '角色名称'")
    private String roleName;

    @Column(name = "descrition",nullable = false,columnDefinition = "varchar(100) comment '描述'")
    private String descrition;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}