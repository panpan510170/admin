package com.pan.controller;

import com.pan.model.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游客
 * @author pan
 * @date 2019/8/2 15:22
 */
@RestController
@RequestMapping("/guest")
public class GuestController extends BaseController{

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public JsonResult login() {
        return this.buildSuccessResult("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public JsonResult submitLogin() {
        return this.buildSuccessResult("您拥有获得该接口的信息的权限！");
    }
}
