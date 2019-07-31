package com.pan.base.util;

/**
 * Created by Pangaofeng on 2018/8/27
 */
public class PageUtils {

    public static int getStartRow(int pageNo,int pageSize) {
        int startRow = ((pageNo==0?1:pageNo) - 1)* pageSize;
        return startRow;
    }

    public static int getEndRow(int pageNo,int pageSize) {
        int endRow = pageSize;
        return endRow;
    }
}
