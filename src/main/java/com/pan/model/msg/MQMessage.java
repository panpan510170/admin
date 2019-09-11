package com.pan.model.msg;

import com.pan.base.handler.MapHandler;

import java.io.Serializable;
import java.util.Map;

/**
 * MQ消息父类
 * 所有生产者发出的消息必须继承该父类
 * @author pan
 * @date 2019/8/29 19:13
 */
public abstract class MQMessage implements Serializable {

    private static final long serialVersionUID = -1387614514139837482L;

    /**
     * 所使用的mq名称
     */
    public String mqName;

    /**
     * 所使用的交换机
     */
    public String exchange;

    /**
     * 所使用的路由
     */
    public String routing;


    /**
     * 构造方法
     */
    public MQMessage(){
        initInfo();
    }

    /**
     * 初始化MQ消息信息 需要加入到每一个构造方法内
     * <br>mqName = ?
     * <br>exchange = ?
     * <br>routing = ?
     */
    public abstract void initInfo();

    /**
     * 将消息转化为Map 并去掉消息中的基础信息
     * @return
     */
    public Map<String, Object> toMap() throws Exception{
        Map<String, Object> map = MapHandler.bean2Map(this);
        map.remove("mqName");
        map.remove("exchange");
        map.remove("routing");
        return map;
    }
}
