package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.model.entitys.system.TUser;
import com.pan.model.vo.JsonResult;
import com.pan.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pangaofeng on 2018/11/13
 */
@RestController
@Api(tags = {"用户"})
@RequestMapping("/user")
public class UserController extends BaseController{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public JsonResult getMessage() {
        return this.buildSuccessResult("您拥有用户权限，可以获得该接口的信息！");
    }

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

    @ApiOperation(value = "查询用户详情")
    @PostMapping(value = "/getUser")
    public JsonResult<TUser> getUser(HttpServletRequest request) throws Exception{

        TUser user = (TUser)request.getAttribute("user");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }

        TUser tUser = userService.getUser(user.getId());

        return this.buildSuccessResult(tUser);
    }
}
