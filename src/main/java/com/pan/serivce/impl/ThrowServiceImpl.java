package com.pan.serivce.impl;

import com.pan.serivce.ThrowService;
import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/26
 */
@Service("throwService")
public class ThrowServiceImpl implements ThrowService {
    @Override
    public Integer testThrow(int a) {
        if(a==0){
            //throw new BOException(ResultCodeEnum.performError.getId(),"测试回滚");
            a = 7/0;
        }
        return a;
    }
}
