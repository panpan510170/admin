package com.pan.controller;

import com.pan.base.handler.MqsHandler;
import com.pan.model.msg.TestMsg;
import com.pan.model.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送异步消息控制器
 * @author pan
 * @date 2019/8/29 19:07
 */
@RestController
@Api(tags = {"发送异步消息控制器"})
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MqsHandler mqsHandler;

    @ApiOperation(value = "发送RabbitMQ消息")
    @PostMapping("/sendMQ")
    public JsonResult sendMQ(@ApiParam(value = "str", required = true) @RequestParam(name = "str") String str) throws Exception{
        TestMsg testMsg = new TestMsg();
        testMsg.setContext(str);
        mqsHandler.sendMsgByRBMQ(testMsg,true);
        return this.buildSuccessResult();
    }

}
