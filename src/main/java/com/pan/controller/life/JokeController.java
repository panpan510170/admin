package com.pan.controller.life;

import com.alibaba.fastjson.JSONObject;
import com.pan.base.constants.LifePrivateKey;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.life.JokeApiUrlEnum;
import com.pan.base.handler.ListHandler;
import com.pan.base.utils.HttpUtils;
import com.pan.base.utils.life.LifeUtils;
import com.pan.controller.BaseController;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.life.ResultVO;
import com.pan.model.vo.life.joke.JokeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 笑话控制层
 * @author pan
 * @date 2019/10/25 18:07
 */
@Slf4j
@RestController
@Api(tags = {"笑话管理"})
@RequestMapping("/joke")
public class JokeController extends BaseController {
    @Autowired
    private LifePrivateKey lifePrivateKey;

    @ApiOperation(value = "随机获取笑话")
    @PostMapping("/randJoke")
    public JsonResult<List<JokeVO>> randJoke() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getJoke());
        String res = HttpUtils.sendGet(JokeApiUrlEnum.randJoke.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<JokeVO> list = ListHandler.jsonString2List(resultVO.getResult(), JokeVO.class);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "最新笑话")
    @PostMapping("/newJoke")
    public JsonResult<List<JokeVO>> newJoke() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getJoke());
        param.put("page",1);
        param.put("pagesize",20);
        String res = HttpUtils.sendGet(JokeApiUrlEnum.newJoke.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()) {
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(), resultVO.getReason());
        }
        JSONObject obj = JSONObject.parseObject(resultVO.getResult());
        List<JokeVO> list = ListHandler.jsonString2List(obj.get("data").toString(), JokeVO.class);
        return this.buildSuccessResult(list);
    }
}
