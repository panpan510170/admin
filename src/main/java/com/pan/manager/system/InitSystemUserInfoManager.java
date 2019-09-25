package com.pan.manager.system;

import com.alibaba.fastjson.JSON;
import com.pan.base.handler.MapHandler;
import com.pan.model.entitys.system.SPermissions;
import com.pan.model.entitys.system.SUser;
import com.pan.serivce.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统迁移后，由springdata-jpa自动创建表,在这里初始化admin用户信息
 * @author pan
 * @date 2019/8/1 17:11
 */
@Slf4j
@Component
@Order(4)
public class InitSystemUserInfoManager implements CommandLineRunner {

    @Autowired
    private SystemService systemService;
    @Value(value="${system.permissionsList}")
    private String permissionsList;

    @Override
    public void run(String... args) throws Exception {
        SUser suser = systemService.getUserByUserName("admin");
        if(null == suser){
            Map<String, Object> param = new HashMap<>();
            param.put("userName","admin");
            param.put("phone","15811015507");
            systemService.addSystemUser(param);
            log.info("初始化admin用户信息完成");
            Map<String, Object> roleParam = new HashMap<>();
            roleParam.put("roleName","管理员");
            roleParam.put("descrition","系统管理员");
            systemService.addRole(roleParam);
            log.info("初始化角色信息完成");
            //构建用户角色
            List<Map<String,Object>> rolePermissionsList = new ArrayList<Map<String,Object>>();
            long roleId = 1;
            Map<String, Object> rolePermissionsParam = new HashMap<>();
            rolePermissionsParam.put("roleId",roleId);
            List<SPermissions> sPermissions = JSON.parseArray(permissionsList, SPermissions.class);
            int index = 1;
            //简析权限信息
            for(SPermissions s : sPermissions){
                log.info("权限配置"+JSON.toJSON(s));
                systemService.addPermissions(MapHandler.bean2Map(s));
                Map<String,Object> map = new HashMap<>();
                map.put("roleId",roleId);
                map.put("id",index++);
                rolePermissionsList.add(map);
            }
            log.info("初始化权限信息完成");
            rolePermissionsParam.put("rolePermissionsList",rolePermissionsList);
            systemService.saveRolePermissionsTree(rolePermissionsParam);
            log.info("初始化角色权限信息完成");
            Map<String, Object> userRoleParam = new HashMap<>();
            userRoleParam.put("userId",1);
            userRoleParam.put("roleId",1);
            systemService.saveUserRole(userRoleParam);
            log.info("初始化用户角色信息完成");
        }
    }
}
