package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.model.LoginLog;
import com.pan.model.User;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.user.UserVO;
import com.pan.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;


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

    @PostMapping("/login1")
    //@Limit(key = "login1", period = 60, count = 20, name = "登录接口", prefix = "limit")
    //@NotBlank(message = "{required}") String verifyCode,
   // boolean rememberMe, HttpServletRequest request
    public JsonResult login1(
            @ApiParam(value = "用户名", required = true)
            @RequestParam(name = "userName") String userName,
            @ApiParam(value = "密码", required = true)
            @RequestParam(name = "password") String password) throws Exception{
        /*if (!CaptchaUtil.verify(verifyCode, request)) {
            throw new FebsException("验证码错误！");
        }*/
        //password = MD5Util.encrypt(username.toLowerCase(), password);
        Boolean rememberMe = false;
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        try {
            super.login(token);
            // 保存登录日志
            LoginLog loginLog = new LoginLog();
            loginLog.setUsername(userName);
            loginLog.setSystemBrowserInfo();
            //this.loginLogService.saveLoginLog(loginLog);

            return this.buildSuccessResult();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            throw new Exception(e.getMessage());
        } catch (AuthenticationException e) {
            throw new BOException(ResultCodeEnum.tokenError.getId(),"认证失败！");
        }
    }

    @PostMapping("/regist")
    public JsonResult regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws Exception {
        //User user = userService.findByName(username);
        User user = null;
        /*if (user != null) {
            throw new FebsException("该用户名已存在");
        }*/
        ///this.userService.regist(username, password);
        return this.buildSuccessResult();
    }

    @GetMapping("/index")
    public JsonResult index(@NotBlank(message = "{required}") @PathVariable String username) {
        // 更新登录时间
        //this.userService.updateLoginTime(username);
        /*Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        //Long totalVisitCount = this.loginLogService.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        //Long todayVisitCount = this.loginLogService.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        //Long todayIp = this.loginLogService.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        User param = new User();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return new FebsResponse().success().data(data);*/
        return  null;
    }

    @GetMapping("images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }
}
