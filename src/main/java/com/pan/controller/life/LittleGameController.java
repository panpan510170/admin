package com.pan.controller.life;

import com.alibaba.fastjson.JSONObject;
import com.pan.base.constants.LifePrivateKey;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.life.LittleGameApiUrlEnum;
import com.pan.base.handler.BeanHandler;
import com.pan.base.handler.MapHandler;
import com.pan.base.utils.HttpUtils;
import com.pan.base.utils.life.LifeUtils;
import com.pan.controller.BaseController;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.life.ResultVO;
import com.pan.model.vo.life.littleGame.QQResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 小游戏
 * @author pan
 * @date 2019/10/25 18:52
 */
@Slf4j
@RestController
@Api(tags = {"小游戏管理"})
@RequestMapping("/littleGame")
public class LittleGameController extends BaseController {

    @Autowired
    private LifePrivateKey lifePrivateKey;

    @ApiOperation(value = "QQ号码测吉凶")
    @PostMapping("/qqevaluate")
    public JsonResult<QQResultVO> qqevaluate(@RequestBody Map paramMap) throws Exception{
        QQResultVO qqResultVO = (QQResultVO) MapHandler.map2Bean(QQResultVO.class, paramMap);
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getQqResult());
        param.put("qq",qqResultVO.getQqCode());
        String res = HttpUtils.sendGet(LittleGameApiUrlEnum.qqResult.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        JSONObject obj = JSONObject.parseObject(resultVO.getResult());
        QQResultVO result = BeanHandler.jsonString2Bean(obj.get("data").toString(), QQResultVO.class);
        return this.buildSuccessResult(result);
    }
}
