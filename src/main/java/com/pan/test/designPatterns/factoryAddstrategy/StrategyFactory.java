package com.pan.test.designPatterns.factoryAddstrategy;

import com.pan.base.enums.PayTypeEnum;

/**
 * 策略工厂 ：工程类提供静态方法，巧妙地利用反射机制和枚举类的valueof方法，返回具体策略实例。
 * @author pan
 * @date 2019/5/29 15:39
 */
public class StrategyFactory {
    public static PayStrategy getStrategy(int type) throws Exception {
        String className = PayTypeEnum.getPayTypeEnumByName(type).getClassName();
        return (PayStrategy) Class.forName(className).newInstance();
    }
}
