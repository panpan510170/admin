package com.pan.controller;

import com.pan.config.shiro.ShiroHelper;
import com.pan.model.entitys.system.SUser;
import com.pan.serivce.SystemService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统后台
 * @author pan
 * @date 2019/8/8 15:08
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private SystemService systemService;
    @Autowired
    private ShiroHelper shiroHelper;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        SUser user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", systemService.getUserByUserName(user.getUserName())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
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
}
