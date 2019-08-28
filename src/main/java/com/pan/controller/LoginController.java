package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.user.UserVO;
import com.pan.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Pangaofeng on 2018/9/6
 */
@Slf4j
@RestController
@Api(tags = {"登录"})
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult<UserVO> login(@ApiParam(value = "用户名", required = true)
                                    @RequestParam(name = "userName") String userName,
                                    @ApiParam(value = "密码", required = true)
                                    @RequestParam(name = "password") String password) throws Exception{

        log.info("LoginController...login...用户登陆接口入参:用户名:[" + userName + "],密码:[" + password + "]");

        if(null == userName) return this.buildErrorResult(ResultCodeEnum.paramError);

        if(null == password) return this.buildErrorResult(ResultCodeEnum.paramError);

        UserVO userVO = userService.login(userName,password);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError);

        return this.buildSuccessResult(userVO);
    }
}
