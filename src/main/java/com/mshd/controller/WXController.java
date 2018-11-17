package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.enums.WXRequestUrlEnum;
import com.mshd.model.User;
import com.mshd.serivce.SendMessageService;
import com.mshd.util.WxTemplateUtils;
import com.mshd.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pangaofeng on 2018/11/16
 */
@Api(tags = {"微信交互"})
@RestController
@RequestMapping(value = {"/wx"})
public class WXController extends BaseController{

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    /**
     * @author: Pangaofeng
     * @date : 2018/8/18
     */
    @ApiOperation(value = "保存fromId--前端")
    @PostMapping("/saveFormId")
    public JsonResult saveFormId(@ApiParam(value = "fromId", required = true)
                                     @RequestParam(name = "fromId") String fromId, HttpServletRequest request) throws Exception{

        log.info("WXController...sendMessage...保存fromId--前端");

        User user = (User) request.getSession().getAttribute("user");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }

        Boolean flag = sendMessageService.saveFormId(user.getId(),fromId);

        if(!flag)  this.buildErrorResult(ResultCodeEnum.bussinessError.getId(),ResultCodeEnum.bussinessError.getName());

        return this.buildSuccessResult();
    }


    /**
     * @author: Pangaofeng
     * @date : 2018/8/18
     */
    @ApiOperation(value = "获取token--测试")
    @PostMapping("/getWXToken")
    public JsonResult getWXToken() throws Exception{

        log.info("WXController...getWXToken...获取token--测试");

        String access_token = WxTemplateUtils.getAccess_token(restTemplate, appId, appSecret, WXRequestUrlEnum.getToken.getUrl());

        return this.buildSuccessResult(access_token);
    }


    /**
     * @author: Pangaofeng
     * @date : 2018/8/18
     */
    @ApiOperation(value = "发送模板消息--测试")
    @PostMapping("/sendMessage")
    public JsonResult sendMessage() throws Exception{

        log.info("WXController...sendMessage...发送模板消息--测试");

        Boolean flag = sendMessageService.sendMessage();

        return this.buildSuccessResult(flag);
    }
}
