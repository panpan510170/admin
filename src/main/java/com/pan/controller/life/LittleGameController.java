package com.pan.controller.life;

import com.alibaba.fastjson.JSONObject;
import com.pan.base.constants.LifePrivateKey;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.life.LittleGameApiUrlEnum;
import com.pan.base.handler.BeanHandler;
import com.pan.base.handler.DataHandler;
import com.pan.base.handler.ListHandler;
import com.pan.base.handler.MapHandler;
import com.pan.base.utils.HttpUtils;
import com.pan.base.utils.life.LifeUtils;
import com.pan.controller.BaseController;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.life.ResultVO;
import com.pan.model.vo.life.littleGame.*;
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

    /**
     * @param paramMap  {"qqCode":"1054064846"}
     * @return
     * @throws Exception
     */
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

    @ApiOperation(value = "周公解梦--梦境类型查询")
    @PostMapping("/category")
    public JsonResult<List<DreamTypeVO>> category() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getDream());
        String res = HttpUtils.sendGet(LittleGameApiUrlEnum.category.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<DreamTypeVO> list = ListHandler.jsonString2List(resultVO.getResult(), DreamTypeVO.class);
        return this.buildSuccessResult(list);
    }

    /**
     * @param paramMap  {"q":"黄金","full":1}
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "周公解梦--解梦查询")
    @PostMapping("/dreamQuery")
    public JsonResult<List<DreamResultVO>> dreamQuery(@RequestBody Map paramMap) throws Exception{
        DreamParamVO dreamParamVO = (DreamParamVO) MapHandler.map2Bean(DreamParamVO.class, paramMap);
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getDream());
        param.put("q",dreamParamVO.getQ());
        if(DataHandler.isNotEmpty(dreamParamVO.getCid())){
            param.put("cid",dreamParamVO.getCid());
        }
        if(DataHandler.isNotEmpty(dreamParamVO.getFull())){
            param.put("full",dreamParamVO.getFull());
        }
        String res = HttpUtils.sendGet(LittleGameApiUrlEnum.dreamQuery.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<DreamResultVO> list = ListHandler.jsonString2List(resultVO.getResult(), DreamResultVO.class);
        return this.buildSuccessResult(list);
    }

    /**
     * @param paramMap  {"date":"2019-11-02"}
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "老黄历--日")
    @PostMapping("/laohuangliD")
    public JsonResult<LaohuangliResultVO> laohuangliD(@RequestBody Map paramMap) throws Exception{
        LaohuangliParamVO paramVO = (LaohuangliParamVO) MapHandler.map2Bean(LaohuangliParamVO.class, paramMap);
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getLaohuangli());
        param.put("date",paramVO.getDate());
        String res = HttpUtils.sendGet(LittleGameApiUrlEnum.laohuangliD.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        LaohuangliResultVO result = BeanHandler.jsonString2Bean(resultVO.getResult(), LaohuangliResultVO.class);
        return this.buildSuccessResult(result);
    }

    /**
     * @param paramMap  {"date":"2019-11-02"}
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "老黄历--时间")
    @PostMapping("/laohuangliH")
    public JsonResult<LaohuangliResultVO> laohuangliH(@RequestBody Map paramMap) throws Exception{
        LaohuangliParamVO paramVO = (LaohuangliParamVO) MapHandler.map2Bean(LaohuangliParamVO.class, paramMap);
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getLaohuangli());
        param.put("date",paramVO.getDate());
        String res = HttpUtils.sendGet(LittleGameApiUrlEnum.laohuangliH.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<LaohuangliResultVO> list = ListHandler.jsonString2List(resultVO.getResult(), LaohuangliResultVO.class);
        return this.buildSuccessResult(list);
    }
}
