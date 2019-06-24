package com.pan.entitys;

import java.lang.annotation.*;

/**
 * @ClassName QueryCondition
 * @Description 自定义注解，用来标识字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface QueryCondition {
    // 数据库中字段名,默认为空字符串,则Query类中的字段要与数据库中字段一致
    String column() default "";

    // equal, like, gt, lt...
    MatchType func() default MatchType.equal;

    // object是否可以为null
    boolean nullable() default false;

    // 字符串是否可为空
    boolean empty() default false;

    // between...and... 查询语句标识， 0时间  1数字类型
    BetweenType type() default BetweenType.datetime;
}
