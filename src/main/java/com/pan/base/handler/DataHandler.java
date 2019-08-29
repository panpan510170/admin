package com.pan.base.handler;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具处理类
 * @author pan
 * @date 2019/8/29 20:51
 */
public class DataHandler {
    /**
     * 对象为空(String Map List Set为空)
     * @return true为空
     */
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }
        if(object instanceof String){
            return "".equals(((String) object).trim());
        }
        else if(object instanceof Map){
            return ((Map<?, ?>) object).isEmpty();
        }
        else if(object instanceof Collection){
            return ((Collection<?>) object).isEmpty();
        }
        return false;
    }

    /**
     * 对象非空(String Map List Set非空)
     * @return true为非空
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 非空返回double值 空或出错返回零
     */
    public static double getDouble(Object obj) {
        return getDouble(obj, 0);
    }

    /**
     * 非空返回double值 空或出错返回默认值
     */
    public static double getDouble(Object obj, int defArg) {
        try {
            if(obj instanceof Number){
                return ((Number)obj).doubleValue();
            }
            return Double.parseDouble(getString(obj, defArg));
        } catch (NumberFormatException e) {
            return defArg;
        }
    }

    /**
     * 非空返回String值 空或出错返回默认值
     */
    public static String getString(Object obj, Object defStr) {
        return isEmpty(obj)?String.valueOf(defStr):String.valueOf(obj);
    }

    /**
     * 非空返回long值 空或出错返回零
     */
    public static long getLong(Object obj) {
        return getLong(obj, 0);
    }

    /**
     * 非空返回long值 空或出错返回默认值
     */
    public static long getLong(Object obj, long defArg) {
        try {
            if(obj instanceof Number){
                return ((Number)obj).longValue();
            }
            return Long.parseLong(getString(obj, defArg));
        } catch (NumberFormatException e) {
            return defArg;
        }
    }
}
