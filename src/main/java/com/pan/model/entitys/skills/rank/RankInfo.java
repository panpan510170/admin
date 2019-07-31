package com.pan.model.entitys.skills.rank;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 榜单数据记录表,记录榜单累计的数据
 * @author pan
 * @date 2019/7/10 11:32
 */
@Entity
@Table(name = "t_rank_info")
@org.hibernate.annotations.Table(appliesTo = "t_rank_info",comment="榜单记录表")
@Data
public class RankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rank_name",nullable = false,columnDefinition = "varchar(100) comment '榜单名称'")
    private String rankName;

    @Column(name = "rounds",columnDefinition = "int default 0 comment '轮次'")
    private Integer rounds;

    @Column(name = "team",columnDefinition = "int default 0 comment '分组'")
    private Integer team;

    @Column(name = "step",columnDefinition = "int default 0 comment '阶段'")
    private Integer step;

    @Column(name = "dateKey",nullable = false,columnDefinition = "int comment '日期'")
    private Integer dateKey;

    @Column(name = "source",nullable = false,columnDefinition = "bigint comment '来源方'")
    private Long source;

    @Column(name = "target",nullable = false,columnDefinition = "bigint comment '目标方'")
    private Long target;

    @Column(name = "score",nullable = false,columnDefinition = "bigint comment '权重累计分值'")
    private Long score;

    @Column(name = "time",nullable = false,columnDefinition = "bigint comment '时间戳'")
    private Long time;

    @Column(name = "bizId",columnDefinition = "bigint comment '关联业务id'")
    private Long bizId;

    @Column(name = "transId",columnDefinition = "bigint comment '二级关联业务id'")
    private Long transId;

    @Column(name = "remark",nullable = false,columnDefinition = "varchar(100) comment '备注'")
    private String remark;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}
