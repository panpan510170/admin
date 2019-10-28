package com.pan.controller.life;

import com.pan.base.constants.LifePrivateKey;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.enums.life.LifeApiUrlEnum;
import com.pan.base.handler.BeanHandler;
import com.pan.base.handler.ListHandler;
import com.pan.base.utils.HttpUtils;
import com.pan.base.utils.life.LifeUtils;
import com.pan.controller.BaseController;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.life.ResultVO;
import com.pan.model.vo.life.life.WeatherCityVO;
import com.pan.model.vo.life.life.WeatherResultVO;
import com.pan.model.vo.life.life.WidTypeVO;
import com.pan.model.vo.life.littleGame.DreamTypeVO;
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
 * 天气，历史上的今天，省份证...管理
 * @author pan
 * @date 2019/10/28 14:25
 */
@Slf4j
@RestController
@Api(tags = {"生活管理"})
@RequestMapping("/life")
public class LifeController extends BaseController {

    @Autowired
    private LifePrivateKey lifePrivateKey;

    @ApiOperation(value = "天气预报--天气种类")
    @PostMapping("/weatherType")
    public JsonResult<List<WidTypeVO>> weatherType() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getSimpleWeather());
        String res = HttpUtils.sendGet(LifeApiUrlEnum.weatherType.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<WidTypeVO> list = ListHandler.jsonString2List(resultVO.getResult(), WidTypeVO.class);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "天气预报--支持城市")
    @PostMapping("/getCityList")
    public JsonResult<List<DreamTypeVO>> getCityList() throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getSimpleWeather());
        String res = HttpUtils.sendGet(LifeApiUrlEnum.getCityList.getUrl(), LifeUtils.buildParamByLife(param));
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        List<WeatherCityVO> list = ListHandler.jsonString2List(resultVO.getResult(), WeatherCityVO.class);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "天气预报")
    @PostMapping("/weather")
    public JsonResult<List<DreamTypeVO>> weather() throws Exception{
        //String cityId = "3";//北京  朝阳
        String cityId = "283";//集宁
        String res = getWeather(cityId);
        ResultVO resultVO = LifeUtils.getData(res);
        if(resultVO.getError_code() != lifePrivateKey.getStatus()){
            return this.buildErrorResult(ResultCodeEnum.thirdError.getId(),resultVO.getReason());
        }
        WeatherResultVO result = BeanHandler.jsonString2Bean(resultVO.getResult(), WeatherResultVO.class);
        return this.buildSuccessResult(result);
    }

    /**
     * 查询天气信息
     * @param cityId  城市id
     * @return
     */
    private String getWeather(String cityId) {
        Map<String, Object> param = new HashMap<>();
        param.put("key",lifePrivateKey.getSimpleWeather());
        param.put("city",cityId);
        String res = HttpUtils.sendGet(LifeApiUrlEnum.weather.getUrl(), LifeUtils.buildParamByLife(param));
        return res;
    }

}
