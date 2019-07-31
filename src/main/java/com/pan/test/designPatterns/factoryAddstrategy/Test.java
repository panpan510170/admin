package com.pan.test.designPatterns.factoryAddstrategy;

import java.math.BigDecimal;

/**
 * 测试工厂模式+策略模式  代替  if elseif if
 * 超过 3 层的 if-else 的逻辑判断代码可以使用卫语句、策略模式、状态模式等来实现
 * @author pan
 * @date 2019/5/29 14:45
 */
public class Test {

    public static void main(String[] args) throws Exception{
        int type = 1;//Param from front end

        PayStrategy strategy = StrategyFactory.getStrategy(type);
        strategy.pay(new BigDecimal(23));

        type = 2;//Param from front end

        strategy = StrategyFactory.getStrategy(2);
        strategy.pay(new BigDecimal(85));
    }
}
