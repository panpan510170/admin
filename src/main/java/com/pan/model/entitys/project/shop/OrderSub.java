package com.pan.model.entitys.project.shop;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单详情表
 * @author pan
 * @date 2019/8/28 14:59
 */
@Entity
@Table(name = "t_order_sub")
@org.hibernate.annotations.Table(appliesTo = "t_order_sub",comment="订单详情表")
@Data
public class OrderSub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_Id",nullable = false,columnDefinition = "bigint(20) comment '订单id'")
    private Long orderId;

    @Column(name = "goods_Id",nullable = false,columnDefinition = "bigint(20) comment '商品id'")
    private Long goodsId;

    @Column(name = "number",nullable = false,columnDefinition = "int comment '商品数量'")
    private int number;

    @Column(name = "price",nullable = false,columnDefinition = "decimal(18,2) comment '商品单价'")
    private BigDecimal price;
}
