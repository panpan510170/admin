package com.mshd.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Pangaofeng on 2018/11/12
 */
public class MD5Utils {

    public static void main(String[] args) {
        System.out.println(md5(md5("111111")));
    }

    /**
     * 生成32位md5码
     * @param password
     * @return
     */ public static String md5(String password) {
         try {
             // 得到一个信息摘要器
             MessageDigest digest = MessageDigest.getInstance("md5");
             byte[] result = digest.digest(password.getBytes());
             StringBuffer buffer = new StringBuffer();
             // 把每一个byte 做一个与运算 0xff;
             for (byte b : result) {
                 // 与运算
                 int number = b & 0xff;
                 // 加盐
                 String str = Integer.toHexString(number);
                 if (str.length() == 1) {
                     buffer.append("0");
                 }
                 buffer.append(str);
             }
             // 标准的md5加密后的结果
             return buffer.toString();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace(); return ""; }
     }
}
