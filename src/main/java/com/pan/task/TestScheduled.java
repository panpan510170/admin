package com.pan.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务启停器
 *
 * Created by Pangaofeng on 2018/9/6
 */
@Slf4j
@Component
public class TestScheduled {

    /*@Scheduled(cron = "0/3 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Scheduled(cron = "0/6 * * * * *")
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(cron = "0/9 * * * * *")
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }*/
}
