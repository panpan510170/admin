package com.mshd.controller;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.model.SRole;
import com.mshd.model.SUser;
import com.mshd.model.TUser;
import com.mshd.serivce.SystemService;
import com.mshd.util.QueryResult;
import com.mshd.vo.JsonResult;
import com.mshd.vo.PermissionsVO;
import com.mshd.vo.user.UserVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@RestController
@Api(tags = {"系统管理"})
@RequestMapping("/system")
public class SystemController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemService systemService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult login(@ApiParam(value = "用户名", required = true)
                            @RequestParam(name = "userName") String userName,
                            @ApiParam(value = "密码", required = true)
                            @RequestParam(name = "password") String password) throws Exception{

        logger.info("SystemController...login...系统用户登陆接口入参:用户名:[" + userName + "],密码:[" + password + "]");

        if(null == userName) return this.buildErrorResult(ResultCodeEnum.paramError);

        if(null == password) return this.buildErrorResult(ResultCodeEnum.paramError);

        UserVO userVO = systemService.login(userName,password);

        if(null == userVO) return this.buildErrorResult(ResultCodeEnum.bussinessError);

        return this.buildSuccessResult(userVO);
    }

    @ApiOperation(value = "系统用户管理")
    @PostMapping("/getUserList")
    public QueryResult getUserList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getUserList...系统用户管理入参:[" + paramMap + "]");

        QueryResult result = systemService.getUserList(paramMap);

        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "角色管理")
    @PostMapping("/getRoleList")
    public QueryResult getRoleList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getRoleList...角色管理入参:[" + paramMap + "]");

        QueryResult result = systemService.getRoleList(paramMap);

        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "所有角色管理")
    @PostMapping("/getRoleAllList")
    public JsonResult getRoleAllList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getRoleAllList...所有角色管理入参:[" + paramMap + "]");

        List<SRole> result = systemService.getRoleAllList(paramMap);

        return this.buildSuccessResult(result);
    }

    @ApiOperation(value = "权限管理")
    @PostMapping("/getPermissionsList")
    public QueryResult getPermissionsList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getPermissionsList...权限管理入参:[" + paramMap + "]");

        QueryResult result = systemService.getPermissionsList(paramMap);

        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "增加权限")
    @PostMapping("/addPermissions")
    public JsonResult addPermissions(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...addPermissions...权限管理入参:[" + paramMap + "]");

        systemService.addPermissions(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "左侧权限列表")
    @PostMapping("/userPermissionsList")
    public JsonResult userPermissionsList(HttpServletRequest request) throws Exception{

        SUser user = (SUser)request.getAttribute("suser");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }

        if(null == user.getId()) return this.buildErrorResult(ResultCodeEnum.paramError);

        List<PermissionsVO> list = systemService.userPermissionsList(user.getId());

        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "查询角色权限列表")
    @PostMapping("/rolePermissionsTreeList")
    public JsonResult rolePermissionsTreeList(@RequestBody Map paramMap,HttpServletRequest request) throws Exception{

        SUser user = (SUser)request.getAttribute("suser");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }

        if(null == user.getId()) return this.buildErrorResult(ResultCodeEnum.paramError);

        List<Map<String,Object>> list = systemService.rolePermissionsTreeList(paramMap);

        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "保存角色权限列表")
    @PostMapping("/saveRolePermissionsTree")
    public JsonResult saveRolePermissionsTree(@RequestBody Map<String,Object> paramMap,HttpServletRequest request) throws Exception{
        SUser user = (SUser)request.getAttribute("suser");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }

        if(null == user.getId()) return this.buildErrorResult(ResultCodeEnum.paramError);

        systemService.saveRolePermissionsTree(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "用户角色管理")
    @PostMapping("/getUserRoleList")
    public QueryResult getUserRoleList(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getUserRoleList...用户角色管理入参:[" + paramMap + "]");

        QueryResult result = systemService.getUserRoleList(paramMap);

        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "用户角色管理")
    @PostMapping("/delPermissions")
    public JsonResult delPermissions(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...getUserRoleList...用户角色管理入参:[" + paramMap + "]");

        systemService.delPermissions(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "增加用户")
    @PostMapping("/addSystemUser")
    public JsonResult addSystemUser(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...addSystemUser...增加用户入参:[" + paramMap + "]");

        systemService.addSystemUser(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateSystemUser")
    public JsonResult updateSystemUser(@RequestBody Map paramMap,HttpServletRequest request) throws Exception{
        logger.info("SystemController...updateSystemUser...修改用户信息入参:[" + paramMap + "]");

        SUser user = (SUser)request.getAttribute("suser");

        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }else{
            paramMap.put("id",user.getId());
        }

        systemService.updateSystemUser(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "增加角色")
    @PostMapping("/addRole")
    public JsonResult addRole(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...addPermissions...增加用户入参:[" + paramMap + "]");

        systemService.addRole(paramMap);

        return this.buildSuccessResult();
    }

    @ApiOperation(value = "保存用户角色")
    @PostMapping("/saveUserRole")
    public JsonResult saveUserRole(@RequestBody Map paramMap) throws Exception{
        logger.info("SystemController...saveUserRole...保存用户角色入参:[" + paramMap + "]");

        systemService.saveUserRole(paramMap);

        return this.buildSuccessResult();
    }

}
