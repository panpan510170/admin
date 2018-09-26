package com.mshd.serivce.impl;

import com.mshd.serivce.TestService;
import com.mshd.serivce.ThrowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pangaofeng on 2018/9/8
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private ThrowService throwService;

    @Override
    public Integer testThread() {
        logger.info("test...thread");
        return null;
    }

    @Override
    public Integer testThrow(int a) {

            Integer i  = throwService.testThrow(a);

        return a;
    }
}
