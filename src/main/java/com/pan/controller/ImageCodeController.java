package com.pan.controller;

import com.pan.base.constants.RedisKeyConstant;
import com.pan.base.utils.CaptchaUtil;
import com.wf.captcha.Captcha;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * 图片验证码
 * @author pan
 * @date 2019/8/26 15:03
 */
@Api(tags = {"图片验证码"})
@RestController
@RequestMapping(value = {"/imageCode"})
public class ImageCodeController extends BaseController{

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getCode")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
        HttpSession session = request.getSession();
        redisTemplate.opsForValue().set(RedisKeyConstant.systemLogin(session.getId()),code,10, TimeUnit.MINUTES);
    }
}
