package com.pan.controller.skills;

import com.pan.controller.BaseController;
import com.pan.manager.skills.webSocket.WebSocketServer;
import com.pan.model.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author pan
 * @date 2019/7/29 10:26
 */
@Api(tags = {"websocket控制器"})
@RestController
@RequestMapping(value = {"/skills/websocket"})
public class WebsocketController extends BaseController {

    @ApiOperation(value = "页面请求")
    @GetMapping("/socket/cid")
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
