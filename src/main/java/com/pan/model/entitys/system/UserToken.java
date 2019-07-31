package com.pan.model.entitys.system;

import java.util.Date;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public class UserToken {

    private Long id;

    private Long userId;

    private String token;

    private Date resetDate;//token重置时间
}
