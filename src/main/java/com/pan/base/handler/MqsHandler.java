package com.pan.base.handler;

import com.pan.model.msg.MQMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author pan
 * @date 2019/8/29 9:36
 */
@Component
@Configuration
public class MqsHandler {
    /*@Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;
*/
    public void sendMessage(MQMessage msg,boolean durable) {
    }
}
