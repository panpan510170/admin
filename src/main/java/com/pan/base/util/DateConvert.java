package com.pan.base.util;

import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;

/**
 * 数据转换实现类
 * @author 呼云飞
 *
 */
public class DateConvert
  implements Converter
{
  @SuppressWarnings("rawtypes")
public Object convert(Class arg0, Object arg1)
  {
    String p = (String)arg1;
    if ((p == null) || (p.trim().length() == 0))
      return null;
    try
    {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      return df.parse(p.trim());
    } catch (Exception e) {
    }
    return null;
  }
}