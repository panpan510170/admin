package com.pan.entitys;

/**
 * @author pan
 * @date 2019/6/24 14:18
 */
public enum MatchType {
    equal,                 // filed = value

    //下面四个用于Number类型的比较
    gt,                     // filed > value
    ge,                     // field >= value
    lt,                     // field < value
    le,                     // field <= value

    notEqual,               // field != value
    like,                   // field like value
    notLike,                // field not like value
    between,                // between value1 and value2 ,Type is Date

    // 下面四个用于可比较类型(Comparable)的比较
    greaterThan,            // field > value
    greaterThanOrEqualTo,   // field >= value
    lessThan,               // field < value
    lessThanOrEqualTo       // field <= value
}
