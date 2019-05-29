package com.pan.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务启停器
 *
 * Created by Pangaofeng on 2018/9/6
 */
@Slf4j
@Component
public class TestScheduled {

    private Logger logger = LoggerFactory.getLogger(TestScheduled.class);

    /*@Scheduled(cron = "0/3 * * * * *")
    public void scheduled(){
        logger.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Scheduled(cron = "0/6 * * * * *")
    public void scheduled1() {
        logger.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(cron = "0/9 * * * * *")
    public void scheduled2() {
        logger.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }*/
}
