package com.mshd.config;

import com.mshd.controller.TestController;
import com.mshd.serivce.TestService;
import com.mshd.util.RedisKey;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;

/**
 * @author pangaofeng
 * @Title: KeyExpiredListener
 * @ProjectName treat-service
 * @Description: redis_Key 注册监听器
 * @date 2018/9/19 21:42
 */
@Component
public class KeyExpiredListener extends JedisPubSub {
    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private TestService testService;

    public static KeyExpiredListener keyExpiredListener;
    /**
     * @Description: 外部依赖注入需执行init方法 才可以使用,否则会报空指针
     * @Author pangaofeng
     * @Date 2018/10/17 13:47
     */
    @PostConstruct
    public void init(){
        keyExpiredListener=this;
        keyExpiredListener.testService = this.testService;
    }
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        if(StringUtils.isNotBlank(message)){
            String[] split = message.split("@");
            if(RedisKey.test.equals(split[0])){
                keyExpiredListener.testService.testRedisTime(split[1]);
            }
        }
    }
}
