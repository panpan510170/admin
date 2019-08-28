package com.pan.serivce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/8
 */
@Slf4j
@Service
public class TestService {

    @Autowired
    private ThrowService throwService;

    public Integer testThread() {
        log.info("test...thread");
        return null;
    }

    public Integer testThrow(int a) {

        Integer i  = throwService.testThrow(a);

        return a;
    }

    public void testRedisTime(String s) {
        log.info("str==="+s);
    }
}
