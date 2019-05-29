package com.pan.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.pan.enums.ResultCodeEnum;
import com.pan.model.User;
import com.pan.util.OSSFileUpLoadUtils;
import com.pan.vo.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(tags = {"阿里云OSS"})
@RestController
@RequestMapping(value = {"/aliyunOss"})
public class AliyunOssController extends BaseController{

    @Value(value = "${aliyun.oss.endpoint}")
    private String endpoint;
    @Value(value = "${aliyun.oss.AccessKeyID}")
    private String AccessKeyID;
    @Value(value = "${aliyun.oss.AccessKeySecret}")
    private String AccessKeySecret;


    @Value(value = "${aliyun.oss.sonAccessKeyID}")
    private String sonAccessKeyID;
    @Value(value = "${aliyun.oss.sonAccessKeySecret}")
    private String sonAccessKeySecret;
    @Value(value = "${aliyun.oss.roleArn}")
    private String roleArn;

    @GetMapping(value = "/getSign")
    public JsonResult<Map<String, String>> getSign(HttpServletRequest request) throws UnsupportedEncodingException {

        String bucket = "midautumn";
        if ("1".equals(request.getHeader("fromId")))
            bucket = "midautumn";
        else if ("2".equals(request.getHeader("fromId")))
            bucket = "midautumn1";
        String dir = "files/";
        String host = "https://" + bucket + "." + endpoint;
        System.out.println(host);
        OSSClient client = new OSSClient(endpoint, AccessKeyID, AccessKeySecret);
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", AccessKeyID);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        //respMap.put("expire", formatISO8601Date(expiration));
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return this.buildSuccessResult(respMap);
    }

    /**
     * 需要在RAM控制台获取，此时要给子账号权限，并建立一个角色，把这个角色赋给子账户，这个角色会有一串值，就是rolearn要填的　　　　　　　
     * 记得角色的权限，子账户的权限要分配好，不然会报错
     *
     * 过期时间默认一小时
     * */

    @GetMapping(value = "/getToken")
    public JsonResult getToken(HttpServletRequest request) throws Exception {

        User user = (User) request.getSession().getAttribute("user");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.paramError.getId(), "用户信息未获取到,请重新登录");
        }

        //临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        String roleSessionName = "MS-"+user.getId();
        String policy = null;
        ProtocolType protocolType = ProtocolType.HTTPS;

        AssumeRoleResponse response = OSSFileUpLoadUtils.assumeRole(sonAccessKeyID, sonAccessKeySecret, roleArn,
                roleSessionName, policy, protocolType);
        String accesskeyid = response.getCredentials().getAccessKeyId();
        String accesskeysecret = response.getCredentials().getAccessKeySecret();

        //这个就是我们想要的安全token　　
        String securitytoken = response.getCredentials().getSecurityToken();
        System.out.println("accesskeyid==="+accesskeyid);
        System.out.println("accesskeysecret==="+accesskeysecret);
        System.out.println("securitytoken==="+securitytoken);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accesskeyid", accesskeyid);
        respMap.put("accesskeysecret", accesskeysecret);
        respMap.put("securitytoken", securitytoken);
        return this.buildSuccessResult(respMap);
    }
}
