package com.pan.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by Pangaofeng on 2018/11/6
 */
public class BaseUtils {
    static final Base64.Decoder decoder = Base64.getDecoder();
    static final Base64.Encoder encoder = Base64.getEncoder();


    public static String decoding(String str){
        String encodedText = "";
        try {
            byte[] textByte = str.getBytes("UTF-8");
            //编码
            encodedText = encoder.encodeToString(textByte);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedText;
    }

    public static String encryption(String str){
        String s = "";
        try {
            s = new String(decoder.decode(str),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void main(String[] args) throws Exception{
        String policyText = "{" + "\"expiration\": \""+213+"\","+"\"conditions\": ["+"[\"content-length-range\", 0, 1048576000]"+"]"+"}";

        System.out.println(decoding(policyText));
        System.out.println(encryption(decoding(policyText)));
    }
}
