package com.pan.controller;

import com.pan.entitys.rank.CoreRank;
import com.pan.handler.DataHandler;
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
import java.util.Date;
import java.util.List;

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
    public JsonResult add(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }


    @ApiOperation(value = "累计榜单")
    @PostMapping("/rank/add")
    public JsonResult addCoreRank(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                  @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                  @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId,
                                  @ApiParam(value = "累计权重", required = true) @RequestParam(name = "weight") long weight){
        //TODO 下面的都没写
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "累计榜单--返回名次")
    @PostMapping("/rank/addResult")
    public JsonResult addResult(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "累计榜单--扣除 不足则全部扣除")
    @PostMapping("/rank/delScore")
    public JsonResult delScore(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "获取排行榜(权重降序)")
    @PostMapping("/rank/list")
    public JsonResult list(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "删除整个排行榜")
    @PostMapping("/rank/del")
    public JsonResult del(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "查询用户排名信息")
    @PostMapping("/rank/get")
    public JsonResult get(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "查询排名第一的用户信息")
    @PostMapping("/rank/first")
    public JsonResult first(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "从排行模型中删除指定用户")
    @PostMapping("/rank/rem")
    public JsonResult rem(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "从排行模型中批量删除指定用户")
    @PostMapping("/rank/remList")
    public JsonResult rem(List<CoreRankVO> coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
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
