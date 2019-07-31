package com.pan.base.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具
 * @author pan
 * @date 2019/6/20 18:33
 */
public class Datehandler {
    public static void main(String[] args) {
        String lastDay = getLastDayOfMonth(2018,10);
        System.out.println("获取2014年2月的最后一天：" + lastDay);

        String currentLDay= getLastDayOfCurrentMonth();
        System.out.println("获取本月的最后一天：" + currentLDay);

        String firstDay = getFirstDayOfMonth(2014,2);
        System.out.println("获取2014年2月的第一天：" + firstDay);

        String currentFDay= getFirstDayOfCurrentMonth();
        System.out.println("获取本月的第一天：" + currentFDay);
    }


    /**
     * 获取某月的最后一天
     */
    public static String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;

    }

    /**
     * 获取这个月的最后一天
     */
    public static String getLastDayOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        //获取某月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 获取某月的第一天
     */
    public static String getFirstDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 获取这个月的第一天
     */
    public static String getFirstDayOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }


    /**
     * 获取当前月的第一天
     * */
    public static String getMonthFirstDay() {
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday = format.format(cale.getTime());
        return firstday + " 00:00:00";
    }

    /**
     * 获取当前月的最后一天
     * */
    public static String getMonthLastDay() {
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastday = format.format(cale.getTime());
        return lastday + " 59:59:59";
    }

    /**
     * 获取当前年份
     * */
    public static Integer getYear() {
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前月份
     * */
    public static Integer getMonth() {
        // 获取前月的第一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取想要的时间值
     * */
    public static void getYouWant() {
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        System.out.println("Current Date: " + cale.getTime());
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        System.out.println("Day of Week: " + dow);
        System.out.println("Day of Month: " + dom);
        System.out.println("Day of Year: " + doy);
    }

    /**
     * 时间加天数
     *
     * */
    public static Date getNextDay(Date date, Integer days) {
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

    /***
     * 获取当前日期距离过期时间的日期差值
     *  日,小时,分钟,秒
     * @param endTime
     * @return
     */
    public static Long dateDiff(String endTime) {
        String strTime = null;
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = sd.format(curDate);
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(str).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff / nh + 1;// 计算差多少小时
            long min = diff / nm + 1;// 计算差多少分钟
            long sec = diff / ns + 1;// 计算差多少秒

            return hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @Description:
     * 比较时间大小  date小  返回ture  反之
     */
    public static Boolean compareDate(Date date, Date end) {
        if(date.getTime() < end.getTime()){
            return true;
        }else{
            return false;
        }
    }
}
