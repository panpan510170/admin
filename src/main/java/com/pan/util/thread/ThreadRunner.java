package com.pan.util.thread;

import com.pan.serivce.TestService;
import com.pan.util.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Pangaofeng on 2018/9/8
 */
@Component
public class ThreadRunner implements Runnable {

    @Autowired
    private TestService testService;

    private static AtomicInteger count = new AtomicInteger(0);

    public ThreadRunner(){
        this.testService = (TestService) SpringBeanUtil.getBeanByName("testService");
    }

    @Override
    public void run(){
        /*if (testService ==null){
            return;
        }*/
        testService.testThread();
        count.addAndGet(1);
        System.out.println("当前线程为：" + Thread.currentThread().getName() + "count:" + count);
    }

    public TestService getTestService() {
        return testService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
