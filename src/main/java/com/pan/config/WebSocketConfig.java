package com.pan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * @author pan
 * @date 2019/6/20 11:46
 *
 * 如果要使用websocket就需要将注解上的注释打开。这里注释掉，是因为与单元测试冲突
 */
//@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
