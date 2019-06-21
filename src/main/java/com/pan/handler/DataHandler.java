package com.pan.handler;

import com.alibaba.fastjson.JSONObject;
import com.pan.util.DateConvert;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
     * ---------------------------------javaBean---------------------------------------------------------------
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
     * javaBean转javaBean
     * @param bean1
     * @param bean2
     * @return
     */
    public static JSONObject beanConver(Object bean1,Object bean2){
        String jsonString = JSONObject.toJSONString(bean1);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        return jsonObject;
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
