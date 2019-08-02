package com.pan.controller;

import com.pan.model.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员
 * @author pan
 * @date 2019/8/2 15:24
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public JsonResult getMessage() {
        return this.buildSuccessResult("您拥有管理员权限，可以获得该接口的信息！");
    }
}
