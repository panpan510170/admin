package com.mshd.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by Pangaofeng on 2018/9/5
 */
public class MapUtils {
    /**
     * 去除空值
     *
     * @param srcMap
     * @param <K>
     * @param <V>
     * @return
     */
    protected <K, V> Map<K, V> removeEnmptyValue(Map<K, V> srcMap) {
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
}
