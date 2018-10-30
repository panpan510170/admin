package com.mshd.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author pangaofeng
 * @Title: RedisSubscribe
 * @ProjectName treat-service
 * @Description: redis key过期事件订阅
 * @date 2018/9/20 14:02
 */
@Configuration
public class RedisSubscribe {
    private static Logger log = LogManager.getLogger(RedisSubscribe.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * @Author pangaofeng
     * @Date 2018/9/20 14:07
     * 订阅过期事件
     */
    public  void psubscribe(){
        log.info("进入订阅 中");
        JedisPool pool = new JedisPool(new JedisPoolConfig(),host,port,1000,password,database);
        Jedis jedis = pool.getResource();
        jedis.psubscribe(new KeyExpiredListener(), "__keyevent@"+database+"__:expired");
    }
}
