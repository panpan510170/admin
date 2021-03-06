package com.pan.model.entitys.skills.limit;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 限制控制表,主要控制限制的使用,清除,维度,时间
 * @author pan
 * @date 2019/7/29 10:15
 */
@Entity
@Table(name = "t_core_limit")
@org.hibernate.annotations.Table(appliesTo = "t_core_limit",comment="限制控制表")
@Data
public class CoreLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '限制名称'")
    private String name;

    @Column(name = "end_time",nullable = false,columnDefinition = "bigint(20) comment '结束使用时间,若无期限可以使用253402271999000(9999-12-31 23:59:59)'")
    private Long endTime;

    @Column(name = "remark",nullable = false,columnDefinition = "varchar(100) comment '备注'")
    private String remark;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Column(name = "pro_name",nullable = false,columnDefinition = "varchar(100) comment '项目名称'")
    private String proName;

    @Transient
    private List<LimitItem> list;
}
