package com.pan.controller;

import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.util.ValidateUtils;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.regist.RegistVO;
import com.pan.model.vo.user.UserVO;
import com.pan.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pangaofeng on 2018/9/6
 */
@Slf4j
@RestController
@Api(tags = {"注册"})
@RequestMapping("/regist")
public class RegistController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/regist")
    public JsonResult<UserVO> regist(RegistVO registVO) throws Exception{

        log.info("RegistController...regist...前端用户注册接口入参:[" + registVO.toString() + "]");

        if(null == registVO) return this.buildErrorResult(ResultCodeEnum.paramError);

        //验证码效验
        Boolean validate = ValidateUtils.validate(registVO);

        if(!validate) return this.buildErrorResult(ResultCodeEnum.paramError.getId(),"验证码错误");

        UserVO userVO = userService.regist(registVO);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(),"注册失败,请稍后重试或联系管理员");

        return this.buildSuccessResult(userVO);
    }

}
