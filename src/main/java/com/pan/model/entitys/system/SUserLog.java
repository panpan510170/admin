package com.pan.model.entitys.system;

import com.pan.model.BaseDO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "s_user_log")
@org.hibernate.annotations.Table(appliesTo = "s_user_log",comment="系统用户操作表")
@Data
public class SUserLog extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false,columnDefinition = "bigint(20) comment '用户id'")
    private Long userId;

    @Column(name = "type",nullable = false,columnDefinition = "int(2) comment '见SystemUserEnum'")
    private Integer type;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

}