package com.pan.base.util.thread;

import com.pan.serivce.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Pangaofeng on 2018/9/8
 */
public class testThread  implements Callable<Integer> {

    private Logger logger = LoggerFactory.getLogger(testThread.class);

    private TestService testService;
    private int start;
    private int end;
    private CountDownLatch countDownLatch;

    public testThread(TestService testService,int start, int end, CountDownLatch countDownLatch) {
        this.testService = testService;
        this.start = start;
        this.end = end;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Integer call() throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("线程:"+Thread.currentThread().getName()+",运行开始");
        int errorCount = 0;
        try {
            testService.testThread();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
            logger.info("线程:"+Thread.currentThread().getName()+",运行结束,时长:"+(System.currentTimeMillis()-startTime));
        }
        return errorCount;
    }
}
