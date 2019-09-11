package com.pan.model.msg;

import lombok.Data;

import java.util.Date;

/**
 * 用户账号操作日志
 * @author pan
 * @date 2019/9/10 11:22
 */
@Data
public class SystemUserLogMsg extends MQMessage{
    private Long userId;
    private Integer type;
    private Date createTime;

    public SystemUserLogMsg() {
    }

    @Override
    public void initInfo() {
        this.mqName = "UserMQ";
        this.exchange = "SystemUserLog";
        this.routing = "SystemUserLogMsg";
    }

    public SystemUserLogMsg(Long userId, Integer type, Date createTime) {
        this.userId = userId;
        this.type = type;
        this.createTime = createTime;
    }
}
