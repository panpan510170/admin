package com.pan.controller.skills;

import com.pan.controller.BaseController;
import com.pan.entitys.skills.rank.CoreRank;
import com.pan.entitys.skills.rank.RankInfo;
import com.pan.handler.DataHandler;
import com.pan.serivce.skills.rank.RankService;
import com.pan.skills.rank.RankManager;
import com.pan.vo.JsonResult;
import com.pan.vo.rank.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author pan
 * @date 2019/7/29 10:24
 */
@Api(tags = {"榜单控制器"})
@RestController
@RequestMapping(value = {"/skills/rank"})
public class RankController extends BaseController {

    @Autowired
    private RankService rankService;

    @Autowired
    private RankManager rankManager;

    @ApiOperation(value = "添加榜单")
    @PostMapping("/addCoreRank")
    public JsonResult add(CoreRankVO coreRankVO){
        CoreRank coreRank = DataHandler.beanConver(coreRankVO, CoreRank.class);
        coreRank.setCreateTime(new Date());
        rankService.save(coreRank);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "持久化榜单数据")
    @PostMapping("/addCoreRankLog")
    public JsonResult addCoreRankLog(RankInfoLogVO rankInfoLogVO){
        RankInfo rankInfo = DataHandler.beanConver(rankInfoLogVO, RankInfo.class);
        rankInfo.setTime(System.currentTimeMillis());
        rankInfo.setCreateTime(new Date());
        rankService.saveLog(rankInfo);
        return this.buildSuccessResult();
    }


    /**
     * 累计榜单
     * @return  返回累计之前的排名信息和累计之后的排名信息
     */
    @ApiOperation(value = "累计榜单")
    @PostMapping("/add")
    public JsonResult<RankAddResultVO> addCoreRank(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                                   @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId,
                                                   @ApiParam(value = "累计权重", required = true) @RequestParam(name = "weight") long weight){
        RankAddResultVO rankAddResultVO =  rankManager.add(new RankVO(name,dimValue,userId,weight,System.currentTimeMillis()));
        return this.buildSuccessResult(rankAddResultVO);
    }

    /**
     * 累计榜单
     * @return  返回上一名和自己和下一名的排名信息
     */
    @ApiOperation(value = "累计榜单--返回名次")
    @PostMapping("/addResult")
    public JsonResult<RankInfoVO> addResult(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                            @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                            @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId,
                                            @ApiParam(value = "累计权重", required = true) @RequestParam(name = "weight") long weight){
        RankInfoVO rankInfoVO =  rankManager.addRank(new RankVO(name,dimValue,userId,weight,System.currentTimeMillis()));
        return this.buildSuccessResult(rankInfoVO);
    }

    @ApiOperation(value = "累计榜单--扣除 不足则全部扣除")
    @PostMapping("/delScore")
    public JsonResult<RankInfoVO> delScore(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                           @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                           @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId,
                                           @ApiParam(value = "累计权重", required = true) @RequestParam(name = "weight") long weight){
        RankInfoVO rankInfoVO = rankManager.delScore(new RankVO(name,dimValue,userId,weight,System.currentTimeMillis()));
        return this.buildSuccessResult(rankInfoVO);
    }

    @ApiOperation(value = "获取排行榜(权重降序)")
    @PostMapping("/list")
    public JsonResult<RankVO> list(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                   @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                   @ApiParam(value = "查询条数", required = true) @RequestParam(name = "size") long size){
        List<RankVO> list = rankManager.list(new RankVO(name,dimValue),size);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "删除整个排行榜")
    @PostMapping("/del")
    public JsonResult<Boolean> del(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                   @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue){
        boolean del = rankManager.del(new RankVO(name, dimValue));
        return this.buildSuccessResult(del);
    }

    @ApiOperation(value = "查询用户排名信息")
    @PostMapping("/get")
    public JsonResult<RankVO> get(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                  @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                  @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId){
        RankVO rankVO = rankManager.get(new RankVO(name,dimValue,userId,System.currentTimeMillis()));
        return this.buildSuccessResult(rankVO);
    }

    @ApiOperation(value = "查询排名第一的用户信息")
    @PostMapping("/first")
    public JsonResult<RankVO> first(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                    @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue){
        RankVO rankVO = rankManager.first(new RankVO(name,dimValue));
        return this.buildSuccessResult(rankVO);
    }

    @ApiOperation(value = "从排行模型中删除指定用户")
    @PostMapping("/rem")
    public JsonResult<Integer> rem(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                   @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                   @ApiParam(value = "用户id", required = true) @RequestParam(name = "userId") long userId){
        int rem = rankManager.rem(new RankVO(name, dimValue,userId,System.currentTimeMillis()));
        return this.buildSuccessResult(rem);
    }

    @ApiOperation(value = "从排行模型中批量删除指定用户(有问题,懒得找)")
    @PostMapping("/remList")
    public JsonResult<Integer> remList(@ApiParam(value = "榜单名称", required = true) @RequestParam(name = "name") String name,
                                       @ApiParam(value = "维度值", required = true) @RequestParam(name = "dimValue") String dimValue,
                                       @ApiParam(value = "用户id集合", required = true) @RequestParam(name = "list") List<String> list){
        int rem = rankManager.remList(new RankVO(name, dimValue),list);
        return this.buildSuccessResult(rem);
    }
}
