package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.serivce.UserService;
import com.mshd.util.ValidateUtils;
import com.mshd.vo.JsonResult;
import com.mshd.vo.regist.RegistVO;
import com.mshd.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/regist")
    public JsonResult<UserVO> regist(RegistVO registVO) throws Exception{

        logger.info("RegistController...regist...前端用户注册接口入参:[" + registVO.toString() + "]");

        if(null == registVO) return this.buildErrorResult(ResultCodeEnum.paramError);

        //验证码效验
        Boolean validate = ValidateUtils.validate(registVO);

        if(!validate) return this.buildErrorResult(ResultCodeEnum.paramError.getId(),"验证码错误");

        UserVO userVO = userService.regist(registVO);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(),"注册失败,请稍后重试或联系管理员");

        return this.buildSuccessResult(userVO);
    }

}
