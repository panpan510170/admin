package com.pan.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class RedisKeyOverdueOpen implements CommandLineRunner {
    @Autowired
    private RedisSubscribe redisSubscribe;
    @Override
    public void run(String... args) throws Exception {
        //随服务开启过期key事件
        log.info("随服务开启过期key事件");
        //redisSubscribe.psubscribe();
    }
}
