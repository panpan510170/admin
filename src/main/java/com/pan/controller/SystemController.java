package com.pan.controller;

import com.pan.base.constants.RedisKeyConstant;
import com.pan.base.enums.ResultCodeEnum;
import com.pan.base.ex.BOException;
import com.pan.base.util.MyUtils;
import com.pan.base.util.QueryResult;
import com.pan.config.shiro.ShiroRealm;
import com.pan.model.entitys.system.SPermissions;
import com.pan.model.entitys.system.SRole;
import com.pan.model.entitys.system.SUser;
import com.pan.model.vo.JsonResult;
import com.pan.model.vo.PermissionsVO;
import com.pan.model.vo.user.UserVO;
import com.pan.serivce.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Pangaofeng on 2018/11/20
 */
@Slf4j
@RestController
@Api(tags = {"系统管理"})
@RequestMapping("/system")
public class SystemController extends BaseController{

    @Autowired
    private SystemService systemService;
    @Autowired
    private ShiroRealm shiroRealm;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult<UserVO> login(@ApiParam(value = "用户名", required = true)
                                        @RequestParam(name = "userName") String userName,
                                    @ApiParam(value = "密码", required = true)
                                        @RequestParam(name = "password") String password,
                                    @ApiParam(value = "验证码", required = true)
                                        @RequestParam(name = "code") String code,HttpServletRequest request) throws Exception{

        log.info("SystemController...login...系统用户登陆接口入参:用户名:[" + userName + "],密码:[" + password + "]");
        //效验验证码
        String vCode = String.valueOf(redisTemplate.opsForValue().get(RedisKeyConstant.systemLogin(request.getSession().getId())));
        if(!code.equals(vCode)){
            throw new BOException(ResultCodeEnum.bussinessError.getId(),"验证码不对！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, false);
        try {
            super.login(token);
            return this.buildSuccessResult();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            throw new Exception(e.getMessage());
        } catch (AuthenticationException e) {
            throw new BOException(ResultCodeEnum.tokenError.getId(),"认证失败！");
        }
    }

    @ApiOperation(value = "系统用户管理")
    @PostMapping("/getUserList")
    public QueryResult<SUser> getUserList(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getUserList...系统用户管理入参:[" + paramMap + "]");
        QueryResult result = systemService.getUserList(paramMap);
        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "角色管理")
    @PostMapping("/getRoleList")
    public QueryResult<SRole> getRoleList(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getRoleList...角色管理入参:[" + paramMap + "]");
        QueryResult result = systemService.getRoleList(paramMap);
        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "所有角色管理")
    @PostMapping("/getRoleAllList")
    public JsonResult<List<SRole>> getRoleAllList(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getRoleAllList...所有角色管理入参:[" + paramMap + "]");
        List<SRole> result = systemService.getRoleAllList(paramMap);
        return this.buildSuccessResult(result);
    }

    @ApiOperation(value = "权限管理")
    @PostMapping("/getPermissionsList")
    public QueryResult<SPermissions> getPermissionsList(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getPermissionsList...权限管理入参:[" + paramMap + "]");
        QueryResult result = systemService.getPermissionsList(paramMap);
        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "增加权限")
    @PostMapping("/addPermissions")
    public JsonResult addPermissions(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...addPermissions...权限管理入参:[" + paramMap + "]");
        systemService.addPermissions(paramMap);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "左侧权限列表")
    @PostMapping("/userPermissionsList")
    public JsonResult<List<PermissionsVO>> userPermissionsList(HttpServletRequest request) throws Exception{
        SUser user = MyUtils.getUserInfo();
        if (null == user || null == user.getId()) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }
        if(null == user.getId()) {
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }
        List<PermissionsVO> list = systemService.userPermissionsList(user.getId());
        return this.buildSuccessResult(list);
    }


    @ApiOperation(value = "查询角色权限列表")
    @PostMapping("/rolePermissionsTreeList")
    public JsonResult<List<Map<String,Object>>> rolePermissionsTreeList(@RequestBody Map paramMap,HttpServletRequest request) throws Exception{
        SUser user = MyUtils.getUserInfo();
        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }
        if(null == user.getId()) {
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }
        List<Map<String,Object>> list = systemService.rolePermissionsTreeList(paramMap);
        return this.buildSuccessResult(list);
    }

    @ApiOperation(value = "保存角色权限列表")
    @PostMapping("/saveRolePermissionsTree")
    public JsonResult saveRolePermissionsTree(@RequestBody Map<String,Object> paramMap,HttpServletRequest request) throws Exception{
        SUser user = MyUtils.getUserInfo();
        if (null == user) {
            return this.buildErrorResult(ResultCodeEnum.bussinessError.getId(), "用户信息未获取到,请重新登录");
        }
        if(null == user.getId()) {
            return this.buildErrorResult(ResultCodeEnum.paramError);
        }
        //清除权限缓存
        shiroRealm.clearCache();
        systemService.saveRolePermissionsTree(paramMap);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "用户角色管理")
    @PostMapping("/getUserRoleList")
    public QueryResult<SUser> getUserRoleList(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getUserRoleList...用户角色管理入参:[" + paramMap + "]");
        QueryResult result = systemService.getUserRoleList(paramMap);
        return this.buildQueryResult(result);
    }

    @ApiOperation(value = "用户角色管理")
    @PostMapping("/delPermissions")
    public JsonResult delPermissions(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...getUserRoleList...用户角色管理入参:[" + paramMap + "]");
        systemService.delPermissions(paramMap);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "增加用户")
    @PostMapping("/addSystemUser")
    public JsonResult addSystemUser(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...addSystemUser...增加用户入参:[" + paramMap + "]");
        systemService.addSystemUser(paramMap);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateSystemUser")
    public JsonResult updateSystemUser(@RequestBody Map paramMap,HttpServletRequest request) throws Exception{
        log.info("SystemController...updateSystemUser...修改用户信息入参:[" + paramMap + "]");
        SUser user = MyUtils.getUserInfo();
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
        log.info("SystemController...addPermissions...增加用户入参:[" + paramMap + "]");
        systemService.addRole(paramMap);
        return this.buildSuccessResult();
    }

    @ApiOperation(value = "保存用户角色")
    @PostMapping("/saveUserRole")
    public JsonResult saveUserRole(@RequestBody Map paramMap) throws Exception{
        log.info("SystemController...saveUserRole...保存用户角色入参:[" + paramMap + "]");
        systemService.saveUserRole(paramMap);
        return this.buildSuccessResult();
    }

}
