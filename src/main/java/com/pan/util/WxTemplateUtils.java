package com.pan.util;

import com.pan.vo.TemplateData;
import com.pan.vo.WxTemplate;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/16
 */
public class WxTemplateUtils {
    private static Logger logger = LogManager.getLogger(WxTemplateUtils.class);
    /**
     * 获取access_token
     * */
    public static String getAccess_token(RestTemplate restTemplate, String appid, String appsecret,String url1) {
        //获取access_token
        String url = url1 + "&appid=" + appid + "&secret=" + appsecret;
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.fromObject(json);
        String access_token = jsonObject.getString("access_token");
        String expires_in = jsonObject.getString("expires_in");
       /* String errcode = jsonObject.getString("errcode");
        String errmsg = jsonObject.getString("errmsg");*/
        return access_token;
    }

    /**
     * 微信小程序创建模板
     */
    public static WxTemplate createTemplate(String openId, String templateId, String formId) {
        //拼接推送的模版
        WxTemplate wxTemplate = new WxTemplate();
        wxTemplate.setTouser(openId);//用户openid
        wxTemplate.setTemplate_id(templateId);//模版id
        wxTemplate.setForm_id(formId);//formid


        Map<String, TemplateData> m = new HashMap<>(5);

        //keyword1：订单类型，keyword2：下单金额，keyword3：配送地址，keyword4：取件地址，keyword5备注
        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("新下单待抢单");
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue("这里填下单金额的值");
        m.put("keyword2", keyword2);
        wxTemplate.setData(m);

        TemplateData keyword3 = new TemplateData();
        keyword3.setValue("这里填配送地址");
        m.put("keyword3", keyword3);
        wxTemplate.setData(m);

        TemplateData keyword4 = new TemplateData();
        keyword4.setValue("这里填取件地址");
        m.put("keyword4", keyword4);
        wxTemplate.setData(m);

        TemplateData keyword5 = new TemplateData();
        keyword5.setValue("这里填备注");
        m.put("keyword5", keyword5);
        wxTemplate.setData(m);

        return wxTemplate;
    }

    /**
     * 发送消息
     */
    public static JSONObject httpRequest(RestTemplate restTemplate,String url,WxTemplate template) {

        String forObject = restTemplate.getForObject(url, String.class, template);
        JSONObject result = JSONObject.fromObject(forObject);

        logger.info("小程序推送结果={}", result);
        return result;
    }

    /**
     * 发送post请求 json格式
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常!" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
