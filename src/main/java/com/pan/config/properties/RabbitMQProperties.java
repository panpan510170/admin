package com.pan.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MrBird
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQProperties {

    private String host;
    private int port;
    private String username;
    private String password;
}
