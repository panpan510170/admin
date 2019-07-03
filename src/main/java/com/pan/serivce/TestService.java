package com.pan.serivce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/8
 */
@Service
public class TestService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ThrowService throwService;

    public Integer testThread() {
        logger.info("test...thread");
        return null;
    }

    public Integer testThrow(int a) {

        Integer i  = throwService.testThrow(a);

        return a;
    }

    public void testRedisTime(String s) {
        logger.info("str==="+s);
    }
}
