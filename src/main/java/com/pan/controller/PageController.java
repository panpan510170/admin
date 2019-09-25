package com.pan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转控制
 * @author pan
 * @date 2019/8/8 15:08
 */
@Controller
public class PageController extends BaseController{

    @RequestMapping(value = "/system/suserList",method = RequestMethod.GET)
    public String suserList() {
        return "system/suserList";
    }

    @RequestMapping(value = "/system/sroleList",method = RequestMethod.GET)
    public String sroleList() {
        return "system/sroleList";
    }

    @RequestMapping(value = "/system/suserRoleList",method = RequestMethod.GET)
    public String suserRoleList() {
        return "system/suserRoleList";
    }

    @RequestMapping(value = "/system/sPermissionsList",method = RequestMethod.GET)
    public String sPermissionsList() {
        return "system/sPermissionsList";
    }

    @RequestMapping(value = "/platForm/send/socketMsg",method = RequestMethod.GET)
    public String sendSocketMsg() {
        return "/platform/sendSocket";
    }

    @RequestMapping(value = "/system/data",method = RequestMethod.GET)
    public String data() {
        return "/data";
    }
}
