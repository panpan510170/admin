package com.mshd.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/9/5
 */
public class ClassUtils {
    /**
     * 获取当前类包括父类的属性 （父类不包括Object）
     *
     * @param clazz
     * @return
     */
    private static Map<String, Field> getThisObjectFields(Class clazz) {
        Map<String, Field> fieldMap = new HashMap<>();
        Field[] thisFields = clazz.getDeclaredFields();
        if (clazz.getSuperclass() != Object.class) {
            Field[] parentFields = clazz.getSuperclass().getDeclaredFields();
            for (Field field : parentFields) {
                fieldMap.put(field.getName(), field);
            }
        }
        for (Field field : thisFields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }
}
