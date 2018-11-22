package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.model.SUser;
import com.mshd.serivce.SystemService;
import com.mshd.util.QueryResult;
import com.mshd.vo.JsonResult;
import com.mshd.vo.PageVO;
import com.mshd.vo.system.UserParamVO;
import com.mshd.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@RestController
@Api(tags = {"系统管理"})
@RequestMapping("/system")
public class SystemController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult login(@ApiParam(value = "用户名", required = true)
                            @RequestParam(name = "userName") String userName,
                            @ApiParam(value = "密码", required = true)
                            @RequestParam(name = "password") String password) throws Exception{

        logger.info("SystemController...login...系统用户登陆接口入参:用户名:[" + userName + "],密码:[" + password + "]");

        if(null == userName) return this.buildErrorResult(ResultCodeEnum.paramError);

        if(null == password) return this.buildErrorResult(ResultCodeEnum.paramError);

        UserVO userVO = systemService.login(userName,password);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError);

        return this.buildSuccessResult(userVO);
    }

    @ApiOperation(value = "系统用户管理")
    @PostMapping("/getUserList")
    public QueryResult getUserList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getUserList...系统用户管理入参:[" + paramMap + "]");

        QueryResult result = systemService.getUserList(paramMap);

        return this.buildQueryResult(result);
    }

}
