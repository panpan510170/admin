package com.mshd.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectAcl;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.mshd.config.Propertyconfig.OSSConfig;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

/**
 * Created by Pangaofeng on 2018/10/09
 */
@Configuration
public class OSSFileUpLoadUtils {


    /**
     * oss上传文件并设置文件权限
     *
     * buckName  文件夹名
     * filename  文件名
     * byteArrayInputStream  文件流
     *
     * return 文件路径
     * */
    public static String ossFileUpload(OSSConfig ossConfig, String buckName, String fileName, ByteArrayInputStream byteArrayInputStream) {

        OSSClient ossClient = null;

        try {
            ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyID(),ossConfig.getAccessKeySecret());

            // 上传Byte数组。byte[] content = "Hello OSS".getBytes();
            ossClient.putObject(buckName, fileName, byteArrayInputStream);

            // 设置文件的访问权限为私有读
            ossClient.setObjectAcl(buckName, fileName, CannedAccessControlList.Private);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭OSSClient。
            ossClient.shutdown();
        }
        return "https://"+buckName+"."+ossConfig.getEndpoint()+"/"+fileName;
    }

    /**
     * 输出文件流  转换  输入文件流
     * */
    public static ByteArrayInputStream outputStreamToInputStream(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    /**
     * oss下载文件流
     *
     * filename  文件名
     *
     * return 文件流
     * */
    public static InputStream getFileIO(OSSConfig ossConfig,String fileName) {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "<yourAccessKeyId>";
        String accessKeySecret = "<yourAccessKeySecret>";
        String bucketName = "<yourBucketName>";
        String objectName = "<yourObjectName>";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);

        InputStream objectContent = ossObject.getObjectContent();

        return objectContent;
    }

    /**
     * 查询文件访问权限等级
     * */
    public static void getPrivateObject(OSSConfig ossConfig,String buckName, String fileName) {
        OSSClient ossClient = null;
        try {
            String AccessKeyID = "LTAIPxACpM1voKll";
            String AccessKeySecret= "85vjeEKlf4bZvlcvHi1XSumy39tfz6";
            String endpoint= "oss-cn-beijing.aliyuncs.com";
            // 创建OSSClient实例。
            ossClient = new OSSClient(endpoint, AccessKeyID,AccessKeySecret);
            // 获取文件的访问权限。
            ObjectAcl objectAcl = ossClient.getObjectAcl(buckName, fileName);
            System.out.println("这是个什么东西"+objectAcl.getPermission().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 获取私有权限的文件
     *
     * buckName  文件夹名
     * filename  文件名
     *
     * */
    public static String getUrl(OSSConfig ossConfig,String buckName, String fileName){
        URL url = null;
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyID(),ossConfig.getAccessKeySecret());
            // 设置URL过期时间为1小时
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            GeneratePresignedUrlRequest generatePresignedUrlRequest ;
            generatePresignedUrlRequest =new GeneratePresignedUrlRequest(buckName, fileName);
            generatePresignedUrlRequest.setExpiration(expiration);
            url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭OSSClient。
            ossClient.shutdown();
        }
        return url.toString();
    }

    /**
     * STS  OSS  获取临时认证
     * */
    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    // 当前 STS API 版本
    public static final String STS_API_VERSION = "2015-04-01";
    //静态方法，方便调用
    public static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                                String roleArn, String roleSessionName, String policy,
                                                ProtocolType protocolType) throws Exception {

        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        // 创建一个 AssumeRoleRequest 并设置请求参数
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(STS_API_VERSION);
        request.setMethod(MethodType.POST);
        request.setProtocol(protocolType);

        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        request.setPolicy(policy);

        // 发起请求，并得到response
        final AssumeRoleResponse response = client.getAcsResponse(request);

        return response;
    }

    public static void main(String[] args) {
        //String accessKeyId = "LTAI8hXNVL2gp1Xt";
        //String accessKeySecret = "Sl5LjsdCaGsemTdHCCtGreacyaUvoe";
        // 需要在RAM控制台获取，此时要给子账号权限，并建立一个角色，把这个角色赋给子账户，这个角色会有一串值，就是rolearn要填的　　　　　　　　　　
        // 记得角色的权限，子账户的权限要分配好，不然会报错
        String accessKeyId = "LTAIHgdiUYGPuRsG";//子账号的accessKeyId
        String accessKeySecret = "2pgaiHc9TKwcUKwrxt0N2LSy3ZS4qn";//子账号的accessKeySecret;　　　　　　　　　　
        //临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        String roleArn = "acs:ram::1861742825276971:role/aliyunosstokengeneratorrole";
        String roleSessionName = "MS456";//这个可以为空，不好写，格式要对，无要求建议为空
        String policy = null;
        ProtocolType protocolType = ProtocolType.HTTPS;
        try {
            AssumeRoleResponse response = OSSFileUpLoadUtils.assumeRole(accessKeyId, accessKeySecret, roleArn,
                    roleSessionName, policy, protocolType);
            String accesskeyid = response.getCredentials().getAccessKeyId();
            String accesskeysecret = response.getCredentials().getAccessKeySecret();
            //这个就是我们想要的安全token　　
            String securitytoken = response.getCredentials().getSecurityToken();
            System.out.println("accesskeyid==="+accesskeyid);
            System.out.println("accesskeysecret==="+accesskeysecret);
            System.out.println("securitytoken==="+securitytoken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
