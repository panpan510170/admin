package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.serivce.UserService;
import com.mshd.vo.JsonResult;
import com.mshd.vo.user.UserRegistVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Pangaofeng on 2018/9/6
 */
@RestController
@Api(tags = {"注册"})
@RequestMapping("/regist")
public class RegistController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(RegistController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/regist")
    public JsonResult regist(@RequestBody(required = false) UserRegistVO userRegistVO) throws Exception{

        logger.info("RegistController...regist...用户注册接口入参:[" + userRegistVO + "]");

        //效验参数

        //效验用户名唯一

        Boolean flag = userService.regist(userRegistVO);

        if(flag){
            return this.buildSuccessResult(flag);
        }else{
            return this.buildErrorResult();
        }
    }
}
