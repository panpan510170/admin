package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "s_permissions")
@org.hibernate.annotations.Table(appliesTo = "s_permissions",comment="系统权限表")
@Data
public class SPermissions extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permissions_name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '权限名称'")
    private String permissionsName;

    @Column(name = "permissions_url",nullable = false,columnDefinition = "varchar(100) comment '权限路径'")
    private String permissionsUrl;

    @Column(name = "permissions_image_url",columnDefinition = "varchar(100) comment '权限图片展示路径'")
    private String permissionsImageUrl;

    @Column(name = "serial_number",nullable = false,columnDefinition = "varchar(100) comment '优先级'")
    private Integer serialNumber;

    @Column(name = "type",nullable = false,columnDefinition = "int comment '类型(1-一级权限,2-二级权限)'")
    private Integer type;

    @Column(name = "parent_id",columnDefinition = "bigint comment '父id'")
    private Long parentId;

    @Column(name = "update_time",nullable = false,columnDefinition = "datetime comment '修改时间'")
    private Date updateTime;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Transient
    private Long userId;
}