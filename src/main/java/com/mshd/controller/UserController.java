package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.serivce.UserService;
import com.mshd.vo.JsonResult;
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
 * Created by Pangaofeng on 2018/11/13
 */
@RestController
@Api(tags = {"用户"})
@RequestMapping("/user")
public class UserController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(RegistController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名唯一效验")
    @PostMapping(value = "/userNameOnly")
    public JsonResult userNameOnly(@ApiParam(value = "用户名", required = true)
                                   @RequestParam(name = "userName") String userName) throws Exception{

        logger.info("RegistController...userNameOnly..用户名唯一效验接口入参:[" + userName + "]");

        if(null == userName) return this.buildErrorResult(ResultCodeEnum.paramError);

        Boolean flag = userService.userNameOnly(userName);

        if(!flag) return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(),"用户名已存在");

        return this.buildSuccessResult();
    }
}
