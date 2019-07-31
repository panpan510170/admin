package com.pan.test.designPatterns.factoryAddstrategy;

import java.math.BigDecimal;

/**
 * 支付宝支付
 * @author pan
 * @date 2019/5/29 15:37
 */
public class AliPay implements PayStrategy{
    @Override
    public void pay(BigDecimal total) {
        System.out.println("pay with alipay: " + total);
    }
}
