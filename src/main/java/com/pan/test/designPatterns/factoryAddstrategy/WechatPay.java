package com.pan.test.designPatterns.factoryAddstrategy;

import java.math.BigDecimal;

/**
 * 微信支付
 * @author pan
 * @date 2019/5/29 15:38
 */
public class WechatPay implements PayStrategy {
    @Override
    public void pay(BigDecimal total) {
        System.out.println("pay with wechatpay: " + total);
    }
}
