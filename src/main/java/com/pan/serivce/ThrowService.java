package com.pan.serivce;

import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/26
 */
@Service
public class ThrowService {
    public Integer testThrow(int a) {
        if(a==0){
            //throw new BOException(ResultCodeEnum.performError.getId(),"测试回滚");
            a = 7/0;
        }
        return a;
    }
}
