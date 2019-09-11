package com.pan.base.handler;

import com.alibaba.fastjson.JSON;
import com.pan.model.msg.MQMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 异步消息工具类
 * @author pan
 * @date 2019/8/29 9:36
 */
@Slf4j
@Component
public class MqsHandler {

    @Autowired
    private RabbitMQHandlerV2 rabbitMQHandlerV2;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * RabbitMQ发送消息--使用rabbitTemplate模板
     * 生产者发送消息 <br>
     * 给队列发送消息=向""交换机的队列名路由发送消息 <br>
     * 发送失败会存于redis中继续发送并通过邮件告知
     * @param msg  MQ消息
     */
    public void sendMsgByRBMQ(MQMessage msg){
        rabbitTemplate.convertAndSend(msg.exchange,msg.routing,msg);
    }

    /**
     * 1-1简单模式：必须消费
     * RabbitMQ发送消息--使用rabbitMQHandler工具
     * 生产者发送消息 <br>
     * 给队列发送消息=向""交换机的队列名路由发送消息 <br>
     * 发送失败会存于redis中继续发送并通过邮件告知
     * @param msg  MQ消息
     * @param durable 消息是否持久化 false = 不需要持久化
     */
    public void sendMsgByRBMQ(MQMessage msg, boolean durable){
        rabbitMQHandlerV2.sendMsgByDirect(msg,durable);
    }

    /**
     * 发布订阅模式：不强制消费,可以绑定多个队列。但需要手动绑定交换机与队列的关系
     * 针对场景：
     *      比如一个商城系统需要在管理员上传商品新的图片时，前台系统必须更新图片，日志系统必须记录相应的日志，
     *      那么就可以将两个队列绑定到图片上传交换器上，一个用于前台系统更新图片，另一个用于日志系统记录日志。
     * RabbitMQ发送消息--使用rabbitMQHandler工具
     * 生产者发送消息 <br>
     * 给队列发送消息=向""交换机的队列名路由发送消息 <br>
     * 发送失败会存于redis中继续发送并通过邮件告知
     * @param msg  MQ消息
     */
    public void sendMsgByRBMQ2Radio(MQMessage msg){
        rabbitMQHandlerV2.sendMsgByFanout(msg);
    }

    /**
     * 1-多：竞争消费者模式：一个生产者对应多个消费者，但是只能有一个消费者获得消息！！！
     * RabbitMQ发送消息--使用rabbitMQHandler工具
     * 生产者发送消息 <br>
     * 给队列发送消息=向""交换机的队列名路由发送消息 <br>
     * 发送失败会存于redis中继续发送并通过邮件告知
     * @param msg  MQ消息
     */
    public void sendMsgByRBMQ2Many(MQMessage msg){
        rabbitMQHandlerV2.sendMsgByFanout(msg);
    }


    /**
     * RabbitMQ简析消息--返回消息体内容
     * @param msg  MQ消息
     * @return 消息字符串
     */
    public String getMsgStr(Message msg){
        try {
            log.debug("Message:"+msg);
            log.debug("Body:"+ JSON.toJSONString(msg.getBody()));
            log.debug("MessageProperties:"+msg.getMessageProperties());
            byte[] body = msg.getBody();
            String message = new String(body, "UTF-8");
            log.info("MsgContext:"+message);
            return message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RabbitMQ简析消息--返回消息体内容
     * @param msg  MQ消息
     * @return 消息体对象
     */
    public <T> T getMsgObj(Message msg, Class<T> t) {
        String msgContext = getMsgStr(msg);
        T obj = BeanHandler.jsonString2Bean(msgContext, t);
        return obj;
    }
}
