package com.pan.worker;

import com.pan.base.handler.MqsHandler;
import com.pan.model.entitys.system.SUserLog;
import com.pan.serivce.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户操作日志监听器
 * @author pan
 * @date 2019/9/10 11:13
 */
@Slf4j
@Service
public class SystemUserOptWorker {

    @Autowired
    private MqsHandler mqsHandler;

    @Autowired
    private SystemService systemService;



    /**
     * 保存系统用户操作日志
     * @param msg
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "SaveSystemUserLog"),
                    exchange = @Exchange(value = "SystemUserLog"),
                    key = "SystemUserLogMsg"
            )})
    public void SaveSystemUserLog(Message msg){
        SUserLog sUserLog = mqsHandler.getMsgObj(msg, SUserLog.class);
        sUserLog.setType(333);
        systemService.saveSyetemUserOptLog(sUserLog);
    }

    /**
     * 测试系统用户操作日志
     * @param msg
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "TestSystemUserLog"),
                    exchange = @Exchange(value = "SystemUserLog"),
                    key = "SystemUserLogMsg"
            )})
    public void receiveWithMessage(Message msg){
        SUserLog sUserLog = mqsHandler.getMsgObj(msg, SUserLog.class);
        sUserLog.setType(444);
        systemService.saveSyetemUserOptLog(sUserLog);
    }
}
