package com.pan.model.entitys.project.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 * @author pan
 * @date 2019/8/28 14:59
 */
@Entity
@Table(name = "t_order")
@org.hibernate.annotations.Table(appliesTo = "t_order",comment="订单表")
@Data
public class Order {
    @Id
    @Column(name = "order_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    //有用么
    @Column(name = "order_no",nullable = false,columnDefinition = "varchar(64) comment '订单号,唯一'")
    private Long orderNo;

    @Column(name = "buy_id",nullable = false,columnDefinition = "bigint(20) comment '购买人id'")
    private Long buyId;

    @Column(name = "trade_type",nullable = false,columnDefinition = "int comment '交易方式(1线上支付,2线上分期,3线下支付)'")
    private Integer tradeType;

    @Column(name = "pay_type",nullable = false,columnDefinition = "int comment '支付方式(1微信,2支付宝,99线下支付)'")
    private Integer payType;

    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}
