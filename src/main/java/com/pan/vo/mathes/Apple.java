package com.pan.vo.mathes;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Pangaofeng on 2018/9/26
 */
@Data
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;
    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}
