package com.pan.entitys.skills.limit;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 限制控制表,主要控制限制的使用,清除,维度,权重,时间
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

/*    @Column(name = "dimension",nullable = false,columnDefinition = "varchar(100) comment '维度标识'")
    private String dimension;

    @Column(name = "number",nullable = false,columnDefinition = "int comment '限制次数'")
    private Integer number;



    @Column(name = "remark",nullable = false,columnDefinition = "varchar(100) comment '备注'")
    private String remark;*/














    @Column(name = "dimension_type",nullable = false,columnDefinition = "int(2) comment '维度 1有2没有'")
    private int dimensionType;

    @Column(name = "basics",nullable = false,columnDefinition = "bigint(20) comment '权重,根据场景自行分配,最小为1'")
    private Long basics;

    @Column(name = "clear_time",columnDefinition = "bigint(20) default 0 comment '清除榜单数据时间,可以为空'")
    private Long clearTime;

    @Column(name = "start_time",nullable = false,columnDefinition = "bigint(20) comment '开始使用时间'")
    private Long startTime;


    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Column(name = "pro_name",nullable = false,columnDefinition = "varchar(100) comment '项目名称'")
    private String proName;
}
