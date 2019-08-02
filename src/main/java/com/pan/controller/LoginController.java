package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.user.UserVO;
import com.pan.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Pangaofeng on 2018/9/6
 */
@RestController
@Api(tags = {"登录"})
@RequestMapping("/login")
public class LoginController extends BaseController{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult<UserVO> login(@ApiParam(value = "用户名", required = true)
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

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login1", method = RequestMethod.POST)
    public JsonResult login1(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = userService.getRoleByUserName(username);
        if ("user".equals(role)) {
            return this.buildSuccessResult("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return this.buildSuccessResult("欢迎来到管理员页面");
        }
        return this.buildErrorResult("权限错误！");
    }
}
