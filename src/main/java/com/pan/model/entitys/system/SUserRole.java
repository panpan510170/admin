package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "s_user_role")
@org.hibernate.annotations.Table(appliesTo = "s_user_role",comment="系统用户角色表")
@Data
public class SUserRole extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false,columnDefinition = "bigint(20) comment '用户id'")
    private Long userId;

    @Column(name = "role_id",nullable = false,columnDefinition = "bigint(20) comment '角色id'")
    private Long roleId;
}