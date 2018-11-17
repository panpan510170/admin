package com.mshd.controller;

import com.alibaba.fastjson.JSONObject;
import com.mshd.enums.ResultCodeEnum;
import com.mshd.ex.BOException;
import com.mshd.serivce.TestService;
import com.mshd.util.RedisKey;
import com.mshd.vo.JsonResult;

import com.mshd.vo.testVO.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


/**
 * Created by Pangaofeng on 2018/8/23
 */
@RestController
@Api(tags = {"测试"})
@RequestMapping("/test")
public class TestController extends BaseController {

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @ApiOperation(value = "测试程序错误")
    @PostMapping("/testLoginErrorException")
    public JsonResult testLoginParamError(@ApiParam(value = "a", required = true)
                                              @RequestParam(name = "a") String a){

        Integer i = testService.testThrow(Integer.parseInt(a));
        if(i != 0){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"商品数量不足");
        }

        return this.buildErrorResult(ResultCodeEnum.performError.getId(), ResultCodeEnum.performError.getName());
    }

    @ApiOperation(value = "测试")
    @PostMapping("/testLogin")
    public JsonResult testLogin(@ApiParam(value = "用户名", required = true)
                                @RequestParam(name = "userName") String userName,
                                @ApiParam(value = "密码", required = true)
                                @RequestParam(name = "password") String password, HttpServletRequest request) {
        log.info("TestController...testLogin...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        JSONObject resMap = null;
        try {
            resMap = new JSONObject();
            resMap.put("userName", userName);
            resMap.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("TestController...testLogin...出参：" + resMap);

        return this.buildSuccessResult(resMap);
    }

    @ApiOperation(value = "测试参数错误")
    @PostMapping("/testLoginParamError")
    public JsonResult testLoginError(@ApiParam(value = "用户名")
                                     @RequestParam(name = "userName") String userName,
                                     @ApiParam(value = "密码", required = true)
                                     @RequestParam(name = "password") String password, HttpServletRequest request) {
        log.info("TestController...testLogin...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        if ("".equals(userName.trim()) || userName == null ||
                "".equals(password) || password == null) {
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }
        JSONObject resMap = null;
        try {
            resMap = new JSONObject();
            resMap.put("userName", userName);
            resMap.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("TestController...testLogin...出参：" + resMap);

        return this.buildSuccessResult(resMap);
    }

    @ApiOperation(value = "测试程序错误")
    @PostMapping("/testLoginError")
    public JsonResult testLoginParamError(@ApiParam(value = "用户名", required = true)
                                          @RequestParam(name = "userName") String userName,
                                          @ApiParam(value = "密码", required = true)
                                          @RequestParam(name = "password") String password, HttpServletRequest request) {
        log.info("TestController...testLogin...入参：用户名：[" + userName + "]，密码：[" + password + "]");


        try {
            int a = 7 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }

        log.info("TestController...testLogin...出参：");

        return this.buildErrorResult(ResultCodeEnum.paramError);
    }

    @ApiOperation(value = "测试BaseVO")
    @PostMapping("/testBaseVO")
    public UserVO testBaseVO(@ApiParam(value = "用户名", required = true)
                                          @RequestParam(name = "userName") String userName,
                                          @ApiParam(value = "密码", required = true)
                                          @RequestParam(name = "password") String password, HttpServletRequest request) {
        log.info("TestController...testBaseVO...入参：用户名：[" + userName + "]，密码：[" + password + "]");

        UserVO userVO = new UserVO();
        userVO.setUserName(userName);
        userVO.setUserNo("213");
        userVO.success();
        return userVO;
    }

    @ApiOperation(value = "测试redisTime")
    @PostMapping("/redisTime")
    public void redisTime(HttpServletRequest request) {
        System.out.println("123");

        redisTemplate.opsForValue().set(RedisKey.TEST+"@"+"1234","1",1*60, TimeUnit.SECONDS);

        System.out.println("456");
    }

}
