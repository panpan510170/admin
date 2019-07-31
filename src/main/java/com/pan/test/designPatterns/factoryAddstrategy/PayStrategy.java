package com.pan.test.designPatterns.factoryAddstrategy;

import java.math.BigDecimal;

/**
 * 付款策略接口
 * @author pan
 * @date 2019/5/29 14:54
 */
public interface PayStrategy {

    void pay(BigDecimal total);
}
