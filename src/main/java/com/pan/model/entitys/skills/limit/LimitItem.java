package com.pan.model.entitys.skills.limit;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 限制项，限制详情
 * @author pan
 * @date 2019/7/29 10:15
 */
@Entity
@Table(name = "t_limit_item")
@org.hibernate.annotations.Table(appliesTo = "t_limit_item",comment="限制项表")
@Data
public class LimitItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_id",nullable = false,columnDefinition = "bigint(20) comment '限制id'")
    private Long limitId;

    @Column(name = "dimension",nullable = false,columnDefinition = "varchar(100) comment '维度标识'")
    private String dimension;

    @Column(name = "type",nullable = false,columnDefinition = "int comment '限制类型1固定(计算限制次数即可),2持续(计算限制次数*时间)'")
    private Integer type;

    @Column(name = "number",nullable = false,columnDefinition = "int comment '限制次数'")
    private Integer number;

    @Column(name = "time",nullable = false,columnDefinition = "bigint comment '时间'")
    private long time;

    @Column(name = "unit",nullable = false,columnDefinition = "varchar(2) comment '时间单位'")
    private String unit;

    @Column(name = "priority",nullable = false,columnDefinition = "int comment '优先级'")
    private int priority;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}
