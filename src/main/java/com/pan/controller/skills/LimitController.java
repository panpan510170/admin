package com.pan.controller.skills;

import com.pan.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pan
 * @date 2019/7/29 10:29
 */
@Api(tags = {"限制控制器"})
@RestController
@RequestMapping(value = {"/skills/limit"})
public class LimitController extends BaseController {
}
