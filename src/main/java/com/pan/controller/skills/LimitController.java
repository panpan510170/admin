package com.pan.controller.skills;

import com.pan.base.handler.BeanHandler;
import com.pan.controller.BaseController;
import com.pan.manager.skills.limit.LimitManager;
import com.pan.model.entitys.skills.limit.CoreLimit;
import com.pan.model.entitys.skills.limit.LimitInfo;
import com.pan.model.entitys.skills.limit.LimitItem;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.limit.AddCoreLimitVO;
import com.pan.model.vo.limit.LimitInfoVO;
import com.pan.model.vo.limit.LimitItemVO;
import com.pan.serivce.skills.limit.LimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pan
 * @date 2019/7/29 10:29
 */
@Api(tags = {"限制控制器"})
@RestController
@RequestMapping(value = {"/skills/limit"})
public class LimitController extends BaseController {

    @Autowired
    private LimitService limitService;

    @Autowired
    private LimitManager limitManager;

    @ApiOperation(value = "新增限制信息")
    @PostMapping("/addCoreLimit")
    public JsonResult add(AddCoreLimitVO coreLimitVO){
        //保存限制信息
        CoreLimit coreLimit = BeanHandler.beanConver(coreLimitVO, CoreLimit.class);
        coreLimit.setCreateTime(new Date());
        CoreLimit cl = limitService.save(coreLimit);
        //转换限制项信息
        List<LimitItem> limitItemList = new ArrayList<>();
        for(LimitItemVO limitItemVO : coreLimitVO.getList()){
            LimitItem limitItem = BeanHandler.beanConver(limitItemVO, LimitItem.class);
            limitItem.setLimitId(cl.getId());
            limitItem.setCreateTime(new Date());
            limitItemList.add(limitItem);
        }
        //保存限制项信息,手动回滚,保存失败,删除限制信息
        limitService.saveLimitItem(limitItemList,cl.getId());
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "增加/减少限制次数")
    @PostMapping("/editLimitCount")
    public JsonResult editLimitCount(LimitInfoVO limitInfoVO){
        //保存限制信息
        LimitInfo limitInfo = BeanHandler.beanConver(limitInfoVO, LimitInfo.class);
        limitInfo.setCreateTime(new Date());
        LimitInfo result = limitManager.editLimitCount(limitInfo);
        return this.buildSuccessResult(result);
    }

    @ApiOperation(value = "查询限制次数信息")
    @PostMapping("/getLimitCountInfo")
    public JsonResult getLimitCountInfo(LimitInfoVO limitInfoVO){
        //保存限制信息
        LimitInfo limitInfo = BeanHandler.beanConver(limitInfoVO, LimitInfo.class);
        LimitInfo result = limitManager.getLimitCountInfo(limitInfo);
        LimitInfoVO res = BeanHandler.beanConver(result, LimitInfoVO.class);
        return this.buildSuccessResult(res);
    }
}
