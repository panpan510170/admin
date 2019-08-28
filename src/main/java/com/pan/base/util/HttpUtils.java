package com.pan.base.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
public class HttpUtils {

    public static void httpPost(String url, List<BasicNameValuePair> formparams)
            throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        try {
            if (null != formparams) {
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
                        formparams, "UTF-8");
                httppost.setEntity(uefEntity);
            }
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            String respData = null;
            try {
                HttpEntity entity = response.getEntity();
            } finally {
                response.close();
            }
        } finally {
            // 关闭连接,释放资源
            httpclient.close();
        }
    }

    /**
     * http post请求传参的方法 返回实体
     */
    public static CloseableHttpResponse httpPostWithPAaram(String url,
                                                           List<BasicNameValuePair> formparams) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        try {
            if (null != formparams) {
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
                        formparams, "UTF-8");
                httppost.setEntity(uefEntity);
            }
            response = httpclient.execute(httppost);
            return response;
        } catch (Exception e) {
            log.error("remote post exception");
        }
        return response;
    }

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String _GET = "GET"; // GET
    private static final String _POST = "POST";// POST
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;

    static String savePath = "/tmp/";
    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @throws IOException
     */
    public static String  downLoadFromUrl(String urlStr,String targeUrl,String fileName){
        try {
           /* import org.apache.commons.httpclient.HttpClient;
            import org.apache.commons.httpclient.HttpMethod;
            import org.apache.commons.httpclient.methods.GetMethod;*/
            //处理301重定向问题
            /*HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(urlStr);
            client.executeMethod(method);
            String realUrl = method.getURI().getURI();//获取到真实地址*/
            String realUrl = null;
            log.info("301-实际地址："+realUrl);
            URL url;
            if(isHttps(realUrl)){
                url = new URL(null, realUrl, new sun.net.www.protocol.https.Handler());
            }else{
                url = new URL(realUrl);
            }
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setUseCaches(false);
            conn.setRequestMethod(_GET);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("User-Agent",  "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            //文件保存位置
            File saveDir = new File(targeUrl);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir+ File.separator+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if(fos!=null){
                fos.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }

            return savePath+fileName;
        } catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            return "";
        }
        //System.out.println("info:"+url+" download success");
    }

    /**
     * 从网络Url中下载文件(对账专用)
     * @param urlStr
     * @param fileName
     * @throws IOException
     */
    public static String  downLoadCheckFileFromUrl(String urlStr,String targeUrl,String fileName) throws Exception {
        URL url;
        if(isHttps(urlStr)){
            url = new URL(null, urlStr, new sun.net.www.protocol.https.Handler());
        }else{
            url = new URL(urlStr);
        }

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setReadTimeout(DEF_READ_TIMEOUT);
        conn.setUseCaches(false);
        conn.setRequestMethod(_GET);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent",  "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(targeUrl);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+ File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        return savePath+fileName;

        //System.out.println("info:"+url+" download success");

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 检测是否https
     *
     * @param url
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }


	/*public static void main(String[] args) throws Exception {
		HttpUtils.downLoadFromUrl("https://image.jinlianchu.com.cn/jlcimage/upload/pdf/CNO_23529_2150034.pdf","/tmp/","tmp.pdf");
	}*/
}
