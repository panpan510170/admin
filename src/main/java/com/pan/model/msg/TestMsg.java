package com.pan.model.msg;

import lombok.Data;

/**
 * @author pan
 * @date 2019/9/9 15:18
 */
@Data
public class TestMsg extends MQMessage {


    private String context;

    @Override
    public void initInfo() {
        this.mqName = "TestMQ";
        this.exchange = "Test";
        this.routing = "Moon2019SendMoonSucMsg";
    }
}
