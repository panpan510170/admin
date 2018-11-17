package com.mshd.util;

/**
 * author:Pangaofeng
 * Date:2018/7/27
 */
public class StringUtils {

    
    /**
     * author:Pangaofeng
     * des:字符串非空判断
     */
    public static Boolean isNull(String str) {
        Boolean flag = true;
        if(null == str){
            flag = false;
        }

        if("".equals(str)){
            flag = false;
        }

        return flag;
    }

    /**
     * author:Pangaofeng
     * des:获取文件类型
     */
    public static String getFileType(String fileName) {
        String str = "";
        if(null != fileName && !"".equals(fileName)){
            str = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        }
        return str;
    }
    /**
     * author:Pangaofeng
     * des:获取文件类型
     */
    public static Boolean isContains(String str,String str1) {
        return str.contains(str1);
    }


    public static void main(String[] args) {
        String str = "C:\\tmp\\10320-DZ-20180725.zip";
        boolean status = str.contains("DZ");
        if(status){
            System.out.println("包含");
        }else{
            System.out.println("不包含");
        }
    }
}
