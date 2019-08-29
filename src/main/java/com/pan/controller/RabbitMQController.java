package com.pan.controller;

import com.pan.base.handler.MqsHandler;
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
 * @author pan
 * @date 2019/8/29 19:07
 */
@RestController
@Api(tags = {"rabbitMQ"})
@RequestMapping("/rabbitMQ")
public class RabbitMQController extends BaseController {

    @Autowired
    private MqsHandler mqsHandler;

    @ApiOperation(value = "发送MQ消息--简单模式")
    @PostMapping("/sendMQEasy")
    public JsonResult sendMQEasy(@ApiParam(value = "str", required = true) @RequestParam(name = "str") String str){

        mqsHandler.sendMessage(null,true);
        return this.buildSuccessResult();
    }

}
