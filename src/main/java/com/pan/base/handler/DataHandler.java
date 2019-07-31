package com.pan.base.handler;

import com.alibaba.fastjson.JSONObject;
import com.pan.base.util.DateConvert;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据处理工具
 * 类型转换，判断空
 * @author pan
 * @date 2019/6/20 18:29
 */
public class DataHandler {
    /**
     * ---------------------------------公用---------------------------------------------------------------
     * */
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
    /**
     * ---------------------------------javaBean---------------------------------------------------------------
     *
     *

     //将对象转换成为字符串

     String str = JSON.toJSONString(infoDo);

     //将字符串转换成为对象

     InfoDo infoDo = JSON.parseObject(strInfoDo, InfoDo.class);

     //将对象集合转换成为字符串

     String users = JSON.toJSONString(users);

     //将字符串转换成为对象集合

     List<User> userList = JSON.parseArray(userStr, User.class);
     *
     * */

    /**
     * javaBean转map
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map bean2Map(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!(propertyName.equals("class"))) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null)
                    returnMap.put(propertyName, result);
                else {
                    returnMap.put(propertyName, null);
                }
            }
        }
        return returnMap;
    }

    /**
     * javaBean转jsonString
     * @param bean
     * @return
     */
    public static String bean2JsonString(Object bean){
        return JSONObject.toJSONString(bean);
    }

    /**
     * javaBean转jsonObject
     * @param bean
     * @return
     */
    public static JSONObject bean2JsonObject(Object bean){
        String jsonString = JSONObject.toJSONString(bean);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject;
    }

    /**
     * JsonString转javaBean
     * @param json
     * @param clazz bean.class
     * @return
     */
    public static <T> T jsonString2Bean(String json,Class<T> clazz){
        T bean = JSONObject.parseObject(json, clazz);
        return bean;
    }

    /**
     * javaBean转javaBean
     * @param bean1
     * @param clazz bean.class
     * @return
     */
    public static <T> T beanConver(Object bean1, Class<T> clazz){
        String jsonString = JSONObject.toJSONString(bean1);
        T bean = JSONObject.parseObject(jsonString, clazz);
        return bean;
    }


    /**
     * ---------------------------------map---------------------------------------------------------------
     * */

    /**
     * map转化javaBean
     * @param type
     * @param map
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static Object Map2Bean(Class type, Map map)
            throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ConvertUtils.register(new DateConvert(), Date.class);

        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        Object obj = type.newInstance();

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!(map.containsKey(propertyName))) continue;
            try {
                Object value = map.get(propertyName);
                BeanUtils.setProperty(obj, propertyName, value);
            } catch (Exception e) {
            }
        }
        return obj;
    }

    /**
     * map去除空值
     *
     * @param srcMap
     * @param <K>
     * @param <V>
     * @return
     */
    protected <K, V> Map<K, V> removeEnmptyValueForMap(Map<K, V> srcMap) {
        if (srcMap == null) {
            return null;
        }
        for (Map.Entry<K, V> entry : srcMap.entrySet()) {
            V value = entry.getValue();
            if (value instanceof String) {
                if (value != null && StringUtils.isBlank((String) value)) {
                    entry.setValue(null);
                }
            }
        }
        return srcMap;
    }

    /**
     * ---------------------------------list---------------------------------------------------------------
     * */

    /**
     * list 随机返回一个下标
     */
    private static Integer listRandomIndex(Integer index){
        int random = (int) (Math.random() * index);
        return random;
    }
}
