package com.pan.controller;

import com.pan.serivce.RankService;
import com.pan.skills.webSocket.WebSocketServer;
import com.pan.vo.JsonResult;
import com.pan.vo.rank.CoreRankVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 工具接口测试
 * @author pan
 * @date 2019/6/20 17:38
 */
@Api(tags = {"工具接口测试(websocket,榜单,限制)"})
@RestController
@RequestMapping(value = {"/skills"})
public class SkillsController extends BaseController{

    @Autowired
    private RankService rankService;

    @ApiOperation(value = "添加榜单")
    @PostMapping("/rank/addCoreRank")
    public JsonResult addCoreRank(CoreRankVO coreRankVO){
        //类转化

        rankService.addCoreRank(coreRankVO);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "页面请求")
    @GetMapping("/websocket/socket/cid")
    public ModelAndView socket(@PathVariable String sid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", sid);
        return mav;
    }

    @ApiOperation(value = "推送数据接口")
    @PostMapping("/websocket/socket/push")
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
