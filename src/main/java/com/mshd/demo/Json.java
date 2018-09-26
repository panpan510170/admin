package com.mshd.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by Pangaofeng on 2018/9/25
 */
public class Json {
    public static void main(String[] args) {
        JSONObject map = new JSONObject();
        map.put("partakeRecord", "参团记录");
        map.put("isPartake", "该团购活动是否已经参与");
//        map.put("orderId", "订单ID，外键");
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteTabAsSpecial));
        System.out.println(JSON.toJSONString(map, SerializerFeature.PrettyFormat));
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteClassName));
        System.out.println(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteSlashAsSpecial));
        System.out.println(JSON.toJSONString(map, SerializerFeature.BrowserCompatible));
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat));
        System.out.println(JSON.toJSONString(map, SerializerFeature.NotWriteRootClassName));
    }
}
