package com.pan.controller.life;

import com.pan.base.constants.LifePrivateKey;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.life.LotteryApiUrlEnum;
import com.pan.base.handler.BeanHandler;
import com.pan.base.handler.ListHandler;
import com.pan.base.handler.MapHandler;
import com.pan.base.utils.HttpUtils;
import com.pan.base.utils.life.LifeUtils;
import com.pan.controller.BaseController;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.life.ResultVO;
import com.pan.model.vo.life.lottery.LotteryResultVO;
import com.pan.model.vo.life.lottery.LotteryTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pan
 * @date 2019/10/23 18:09
 */
@Slf4j
@RestController
@Api(tags = {"彩票管理"})
@RequestMapping("/lottery")
public class LotteryController extends BaseController {

    @Autowired
    private LifePrivateKey lifePrivateKey;

    @ApiOperation(value = "查询彩票种类")
    @PostMapping("/selectType")
    public JsonResult<List<LotteryTypeVO>> selectType() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getLottery());
        String res = HttpUtils.sendGet(LotteryApiUrlEnum.selectType.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<LotteryTypeVO> list = ListHandler.jsonString2List(resultVO.getResult(), LotteryTypeVO.class);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "查询开奖结果")
    @PostMapping("/selectResult")
    public JsonResult<List<LotteryTypeVO>> selectResult(@RequestBody Map paramMap) throws Exception{
        LotteryTypeVO lotteryTypeVO = (LotteryTypeVO)MapHandler.map2Bean(LotteryTypeVO.class, paramMap);
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getLottery());
        param.put("lottery_id",lotteryTypeVO.getLottery_id());
        String res = HttpUtils.sendGet(LotteryApiUrlEnum.selectResult.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        LotteryResultVO lotteryResultVO = BeanHandler.jsonString2Bean(resultVO.getResult(), LotteryResultVO.class);
        return this.buildSuccessResult(lotteryResultVO);
    }
}
