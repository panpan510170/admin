package com.mshd.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pangaofeng on 2018/8/28
 *
 * DES:生成系统编号使用
 */
public class GenerateNO {
    public static void main(String[] args) {
        System.out.println(getPrefixNO("DD"));

    }
    /**
     * 获取带前缀的系统编号
     * @return result
     */
    public static String getPrefixNO(String prefix) {
        //格式化当前时间
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDate = sfDate.format(new Date());
        //得到17位时间如：20170411094039080
        //System.out.println("时间17位：" + strDate);
        //为了防止高并发重复,再获取3个随机数
        String random = getRandom620(6);

        //System.out.println("订单号20位：" + strDate + random);

        return prefix+strDate+random;
    }

    /**
     * 获取6-10 的随机位数数字
     * @param length	想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }

    /**
     * @param peopleNum
     * @Return java.lang.String
     * @Description: 根据人数生成活动标签
     * @Author chaixueteng
     * @Date 2018/8/31 11:15
     */
    public static String getActivityTag(Integer peopleNum){
        StringBuffer stringBuffer = new StringBuffer();
        if(peopleNum!=null){
            stringBuffer.append(peopleNum);
            stringBuffer.append("人成团");
        }
        return stringBuffer.toString();
    }

    /**
     * 返回活动商品价格 最低的
     * @param goodsInfo
     * @author chaixueteng
     * @time 2018-8-8
     */
    public static BigDecimal getLowPrice(String goodsInfo){
        if(StringUtils.isBlank(goodsInfo)){
            return null;
        }
        List<JSONObject> jsonObjects = JSONObject.parseArray(goodsInfo, JSONObject.class);
        List<BigDecimal> priceList = new ArrayList<BigDecimal>();
        if(jsonObjects.size()>0){
            for (int i = 0; i <jsonObjects.size() ; i++) {
                JSONObject jsonObject = jsonObjects.get(i);
                BigDecimal price = jsonObject.getBigDecimal("price");
                priceList.add(price);
            }
        }else{
            return null;
        }
        if(priceList.size()>0){
            BigDecimal price = Collections.min(priceList).setScale(2, BigDecimal.ROUND_DOWN);
            return price;
        }
        return null;
    }
    /**
     * 返回活动原价 第一个商品价格作为原价
     * @param goodsInfo
     * @author chaixueteng
     * @time 2018-8-8
     */
    public static BigDecimal getOriginalPrice(String goodsInfo){
        if(StringUtils.isBlank(goodsInfo)){
            return null;
        }
        List<JSONObject> jsonObjects = JSONObject.parseArray(goodsInfo, JSONObject.class);
        if(jsonObjects.size()>0){
            JSONObject jsonObject = jsonObjects.get(0);
            BigDecimal price = jsonObject.getBigDecimal("prices");
            return price;
        }else{
            return null;
        }
    }
}
