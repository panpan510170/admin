package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "s_role_permissions")
@org.hibernate.annotations.Table(appliesTo = "s_role_permissions",comment="系统角色权限表")
@Data
public class SRolePermissions extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id",nullable = false,columnDefinition = "bigint comment '角色id'")
    private Long roleId;

    @Column(name = "permissions_id",nullable = false,columnDefinition = "bigint comment '权限id'")
    private Long permissionsId;
}