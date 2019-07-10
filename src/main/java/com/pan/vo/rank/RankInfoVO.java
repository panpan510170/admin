package com.pan.vo.rank;

import lombok.Data;

/**
 * @author pan
 * @date 2019/7/8 17:52
 */
@Data
public class RankInfoVO {
    //上一名
    private RankVO former;
    //自己
    private RankVO self;
    //下一名
    private RankVO latter;
}
