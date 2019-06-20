package com.pan.controller;

import com.pan.skills.webSocket.WebSocketServer;
import com.pan.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * websocket 实现消息主动推送
 * @author pan
 * @date 2019/6/20 14:47
 */
@Api(tags = {"websocket测试"})
@RestController
@RequestMapping(value = {"/websocket"})
public class WebSocketController extends BaseController{

    @ApiOperation(value = "页面请求")
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String sid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", sid);
        return mav;
    }

    @ApiOperation(value = "推送数据接口")
    @PostMapping("/socket/push")
    public JsonResult pushToWeb(@ApiParam(value = "用户表示", required = true)
                                    @RequestParam(name = "sid") String sid, String message) {
        try {
            WebSocketServer.sendInfo(message, sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.buildSuccessResult();
    }
}
