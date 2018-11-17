package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.serivce.UserService;
import com.mshd.vo.JsonResult;
import com.mshd.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Pangaofeng on 2018/9/6
 */
@RestController
@Api(tags = {"登录"})
@RequestMapping("/login")
public class LoginController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult login(@ApiParam(value = "用户名", required = true)
                                @RequestParam(name = "userName") String userName,
                      @ApiParam(value = "密码", required = true)
                                @RequestParam(name = "password") String password) throws Exception{

        logger.info("LoginController...login...用户登陆接口入参:用户名:[" + userName + "],密码:[" + password + "]");

        if(null == userName) return this.buildErrorResult(ResultCodeEnum.paramError);

        if(null == password) return this.buildErrorResult(ResultCodeEnum.paramError);

        UserVO userVO = userService.login(userName,password);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError);

        return this.buildSuccessResult(userVO);
    }
}
