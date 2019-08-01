package com.pan.model.entitys.skills.limit;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 限制信息详情
 * @author pan
 * @date 2019/7/29 10:15
 */
@Entity
@Table(name = "t_limit_info")
@org.hibernate.annotations.Table(appliesTo = "t_limit_info",comment="限制信息表")
@Data
public class LimitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_id",nullable = false,columnDefinition = "bigint(20) comment '限制id'")
    private Long limitId;

    @Column(name = "dimension",nullable = false,columnDefinition = "varchar(100) comment '维度标识'")
    private String dimension;

    @Column(name = "number",nullable = false,columnDefinition = "int comment '限制次数'")
    private Integer number;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}
