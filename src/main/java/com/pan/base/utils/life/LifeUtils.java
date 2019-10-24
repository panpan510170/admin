package com.pan.base.utils.life;

import com.pan.base.handler.BeanHandler;
import com.pan.base.handler.ListHandler;
import com.pan.model.vo.life.ResultVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 生活服务api工具类
 * @author pan
 * @date 2019/10/24 10:14
 */
@Slf4j
public class LifeUtils {
    /**
     * 生活服务api接口的参数拼接
     * @param param
     * @return
     */
    public static String buildParamByLife(Map<String, Object> param) {
        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        String str = "";
        int index = 1;
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if(index == 1){
                str += entry.getKey() + "=" + entry.getValue();
            }else{
                str += "&" + entry.getKey() + "=" + entry.getValue();
            }
            index++;
        }
        log.info("生活服务api接口"+ str);
        return str;
    }

    /**
     * 获取接口调用结果
     * @param res  结果json
     */
    public static ResultVO getData(String res) {
        return BeanHandler.jsonString2Bean(res, ResultVO.class);
    }

    /**
     * 解析结果数据
     * @param result  结果数据
     * @param clazz   返回类型
     * @param <T>    泛型
     * @return
     */
    public static <T> List<T> getListData(String result, Class<T> clazz) {
        return ListHandler.jsonString2List(result, clazz);
    }
}
