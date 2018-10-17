package com.mshd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pangaofeng on 2018/10/9
 */
public class DateUtils {

    public static void main(String[] args) {
        System.out.println(getNextDay(new Date(),3));
    }
    /**
     * 时间加天数
     *
     * */
    public static Date getNextDay(Date date,Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +days);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取时间差
     *
     * days : 想获取的时间和当前时间相差的天数
     * */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        String time = "";
        if(day != 0){
            return day + "天" + hour + "小时" + min + "分钟" +sec + "秒";
        }else{
            if(hour != 0){
                return  hour + "小时" + min + "分钟" +sec + "秒";
            }else{
                if(min != 0){
                    return  min + "分钟" +sec + "秒";
                }else{
                    return  sec + "秒";
                }

            }

        }

    }

    /**
     * * 两个时间之间相差距离多少天
     * * @param one 时间参数 1：
     * * @param two 时间参数 2：
     * @return 相差天数
     * */
    public static long getDistanceDays(Date str1, Date str2) {
        long days=0;
        try {
            long time1 = str1.getTime();
            long time2 = str2.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取当前时间
     *
     * days : 想获取的时间和当前时间相差的天数
     * */
    public static Date getWantDate(Integer days){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days*-1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 将时间转换为字符串
     *
     * dateValue : 时间
     * format : 格式
     * */
    public static String dateToString(Date dateValue, String format) {
        if (dateValue == null) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(dateValue);
        }
    }
}
