package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.model.LoginLog;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.PermissionsVO;
import com.pan.serivce.SystemService;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author pan
 * @date 2019/8/8 15:08
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/unauthorized",method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }


    @RequestMapping(value = "/login1")
    @ResponseBody
    public JsonResult login1(@RequestParam(value = "userName",required = false) String userName,
                             @RequestParam(value = "password",required = false) String password) throws Exception{
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

    @RequestMapping("/userPermissionsList")
    @ResponseBody
    public JsonResult<List<PermissionsVO>> userPermissionsList(HttpServletRequest request) throws Exception{

        List<PermissionsVO> list = systemService.userPermissionsList(1L);

        return this.buildSuccessResult(list);
    }
}
