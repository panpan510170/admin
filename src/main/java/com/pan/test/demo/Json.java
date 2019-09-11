package com.pan.test.demo;

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
        map.put("isPartake2", null);
//        map.put("orderId", "订单ID，外键");
        //紧凑格式  {"isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteTabAsSpecial));
        //标准格式
        //{
        //	"isPartake":"该团购活动是否已经参与",
        //	"partakeRecord":"参团记录"
        //}
        System.out.println(JSON.toJSONString(map, SerializerFeature.PrettyFormat));
        //有类名的紧凑格式  {"@type":"com.alibaba.fastjson.JSONObject","isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteClassName));
        //紧凑格式  {"isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
        //紧凑格式  {"isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteSlashAsSpecial));
        //code码紧凑格式  {"isPartake":"\u8BE5\u56E2\u8D2D\u6D3B\u52A8\u662F\u5426\u5DF2\u7ECF\u53C2\u4E0E","partakeRecord":"\u53C2\u56E2\u8BB0\u5F55"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.BrowserCompatible));
        //紧凑格式  {"isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat));
        //紧凑格式  {"isPartake":"该团购活动是否已经参与","partakeRecord":"参团记录"}
        System.out.println(JSON.toJSONString(map, SerializerFeature.NotWriteRootClassName));
    }
}
