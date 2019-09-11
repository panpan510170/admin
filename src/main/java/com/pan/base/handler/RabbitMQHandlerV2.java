package com.pan.base.handler;

import com.alibaba.fastjson.JSON;
import com.pan.config.properties.RabbitMQProperties;
import com.pan.model.msg.MQMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ消息助手
 * @author pan
 * @date 2019/9/9 14:36
 */
@Slf4j
@Component
public class RabbitMQHandlerV2 {
    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    /**
     * 获取连接rabbitMQ连接工厂
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        //1、定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2、设置服务器地址
        factory.setHost(rabbitMQProperties.getHost());
        //3、设置端口
        factory.setPort(rabbitMQProperties.getPort());
        //4、设置虚拟主机、用户名、密码
        factory.setVirtualHost("/");
        factory.setUsername(rabbitMQProperties.getUsername());
        factory.setPassword(rabbitMQProperties.getPassword());
        //5、通过连接工厂获取连接
        com.rabbitmq.client.Connection connection = factory.newConnection();
        return connection;
    }

    /**
     * 发送消息--简单模式1对1
     * @param msg
     * @param durable
     */
    public void sendMsgByDirect(MQMessage msg, boolean durable) {
        Connection connection = null;
        Channel channel = null;
        try {
            //创建通道
            connection = getConnection();
            channel = connection.createChannel();
            //声明交换机
            //判断交换机存不存在
            if(DataHandler.isEmpty(msg.exchange)){
                //若是交换机没有传，就用路由key先创建一个交换机
                channel.exchangeDeclare(msg.exchange, "direct",true);
            }else{
                channel.exchangeDeclare(msg.routing, "direct",true);
            }
            //发布消息
            channel.basicPublish(msg.exchange, msg.routing, null, JSON.toJSONBytes(msg));
        } catch (IOException e) {
            log.error("RabbitMQHandlerV2-工具,无法创建{}的交换机",msg.exchange);
        } catch (Exception e) {
            log.error("RabbitMQHandlerV2-工具,无法创建关闭连接");
        }finally {
            close(connection,channel);
        }
    }

    /**
     * 发送消息--广播模式
     * @param msg 消息
     */
    public void sendMsgByFanout(MQMessage msg) {
        Connection connection = null;
        Channel channel = null;
        try {
            //创建通道
            connection = getConnection();
            channel = connection.createChannel();
            //声明交换机
            channel.exchangeDeclare(msg.exchange, "fanout");
            //发布消息
            channel.basicPublish(msg.exchange, "", null, JSON.toJSONBytes(msg));
        } catch (IOException e) {
            log.error("RabbitMQHandlerV2-工具--广播模式,无法创建{}的交换机",msg.exchange);
        } catch (Exception e) {
            log.error("RabbitMQHandlerV2-工具--广播模式,无法创建关闭连接");
        }finally {
            close(connection,channel);
        }
    }

    private void close(Connection connection, Channel channel) {
        try {
            //6、关闭通道
            channel.close();
            //7、关闭连接
            connection.close();
        } catch (IOException e) {
            log.error("RabbitMQHandlerV2-工具,关闭连接池，通过失败");
            e.printStackTrace();
        } catch (TimeoutException e) {
            log.error("RabbitMQHandlerV2-工具,关闭连接池，通过超时");
            e.printStackTrace();
        }
    }
}
