package com.pan.serivce;

import com.pan.base.enums.WXRequestUrlEnum;
import com.pan.base.util.WxTemplateUtils;
import com.pan.model.vo.WxTemplate;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Pangaofeng on 2018/11/16
 */
@Service
public class SendMessageService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    public Boolean sendMessage() {
        //先从缓存中获取token  若没有则从微信中获取

        //获取token
        String access_token = WxTemplateUtils.getAccess_token(restTemplate, appId, appSecret, WXRequestUrlEnum.getToken.getUrl());


        String openId = "oK_pJ5MEhtZsJRZHy7pG0jnJIzDs";
        String formId = "1542360596288";
        //封装内容
        //WxTemplate template = WxTemplateUtils.createTemplate(openId, WXTemplateMessageEnum.nopayOrderRemind.getTemplateId(), formId);
        WxTemplate template = WxTemplateUtils.createTemplate(openId, "PkfYs7r-R1yUVWS-jJNZgw3cnExQqqzCYysxuHs7K58", formId);
        JSONObject json = JSONObject.fromObject(template);
        String param = json.toString();
        //发送模板消息
        String s = WxTemplateUtils.sendPost(WXRequestUrlEnum.sendMessage.getUrl() + "?access_token=" + access_token, param);
        System.out.println(s);
        return true;
    }

    public Boolean saveFormId(Long id, String fromId) {

        return true;
    }
}
