package com.pan.admin;

import com.pan.serivce.TestService;
import com.pan.util.thread.ThreadRunner;
import com.pan.util.thread.testThread;
import com.pan.vo.Constants;
import com.pan.vo.PageVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程测试类
 *
 * Created by Pangaofeng on 2018/9/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTests {
    @Autowired
    private ThreadRunner threadRunner;

    @Autowired
    private TestService testService;


    /**
     * newFixedThreadPool  创建线程池
     * CountDownLatch   管理线程
     *
     * 此方法是早期编写,较为稳定.但使用方法较老
     */
    @Test
    public void threadDemo() throws Exception {
        ArrayList<Object> list = new ArrayList<>();

        list.add(new PageVO());
        list.add(new PageVO());
        list.add(new PageVO());
        list.add(new PageVO());

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Constants.threadsNum);
        ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService<Integer>(newFixedThreadPool);
        int pSize = Constants.threadsNumber;//每条线程处理数据条数
        int errorCount = 0;//错误记录数
        int size = list.size();//要处理的数据量

        //线程处理数
        int threadSize = (size % pSize)==0?size / pSize:size / pSize + 1;

        //线程控制
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for(int i = 0; i < threadSize; i++){
            int start = i * pSize;
            int end = (i + 1) * pSize - 1 ;
            if(end > size)
                end = size-1;
            ecs.submit(new testThread(testService,start,end,countDownLatch));
        }
        countDownLatch.await();

        for(int i = 0; i < threadSize; i++){
            //获取线程返回结果
            errorCount += ecs.take().get();
        }
    }


    /**
     * 简单测试多线程调用接口
     */
    @Test
    public void testThread() {
        for (int i=0; i<3; i++){
            //new Thread(new ThreadRunner()).start();
            new Thread(threadRunner).start();
        }

        /*try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
    }
}
