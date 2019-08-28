package com.pan.manager.system;

import com.pan.model.entitys.system.SUser;
import com.pan.serivce.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

            Map<String, Object> permissionsParam = new HashMap<>();
            permissionsParam.put("permissionsName","系统管理");
            permissionsParam.put("type",1);
            permissionsParam.put("serialNumber",1);
            permissionsParam.put("permissionsUrl","/");
            permissionsParam.put("parentId",0);
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","用户管理");
            permissionsParam.put("type",2);
            permissionsParam.put("serialNumber",1);
            permissionsParam.put("parentId",1);
            permissionsParam.put("permissionsUrl","/system/suserList");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","角色管理");
            permissionsParam.put("type",2);
            permissionsParam.put("serialNumber",2);
            permissionsParam.put("parentId",1);
            permissionsParam.put("permissionsUrl","/system/sroleList");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","权限管理");
            permissionsParam.put("type",2);
            permissionsParam.put("serialNumber",3);
            permissionsParam.put("parentId",1);
            permissionsParam.put("permissionsUrl","/system/sPermissionsList");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","用户角色管理");
            permissionsParam.put("type",2);
            permissionsParam.put("serialNumber",4);
            permissionsParam.put("parentId",1);
            permissionsParam.put("permissionsUrl","/system/suserRoleList");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","增加用户");
            permissionsParam.put("type",3);
            permissionsParam.put("serialNumber",1);
            permissionsParam.put("parentId",2);
            permissionsParam.put("permissionsUrl","/system/addSystemUser");
            permissionsParam.put("permissions","user:add");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","增加权限");
            permissionsParam.put("type",3);
            permissionsParam.put("serialNumber",1);
            permissionsParam.put("parentId",2);
            permissionsParam.put("permissionsUrl","/system/addPermissions");
            permissionsParam.put("permissions","permissions:add");
            systemService.addPermissions(permissionsParam);
            permissionsParam.put("permissionsName","增加角色");
            permissionsParam.put("type",3);
            permissionsParam.put("serialNumber",1);
            permissionsParam.put("parentId",2);
            permissionsParam.put("permissionsUrl","/system/addRole");
            permissionsParam.put("permissions","role:add");
            systemService.addPermissions(permissionsParam);
            log.info("初始化权限信息完成");

            Map<String, Object> rolePermissionsParam = new HashMap<>();
            long roleId = 1;
            rolePermissionsParam.put("roleId",roleId);
            List<Map<String,Object>> rolePermissionsList = new ArrayList<Map<String,Object>>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("roleId",roleId);
            map1.put("id",1);
            rolePermissionsList.add(map1);
            Map<String,Object> map2 = new HashMap<>();
            map2.put("roleId",roleId);
            map2.put("id",2);
            rolePermissionsList.add(map2);
            Map<String,Object> map3 = new HashMap<>();
            map3.put("roleId",roleId);
            map3.put("id",3);
            rolePermissionsList.add(map3);
            Map<String,Object> map4 = new HashMap<>();
            map4.put("roleId",roleId);
            map4.put("id",4);
            rolePermissionsList.add(map4);
            Map<String,Object> map5 = new HashMap<>();
            map5.put("roleId",roleId);
            map5.put("id",5);
            rolePermissionsList.add(map5);
            Map<String,Object> map6 = new HashMap<>();
            map6.put("roleId",roleId);
            map6.put("id",6);
            rolePermissionsList.add(map6);
            Map<String,Object> map7 = new HashMap<>();
            map7.put("roleId",roleId);
            map7.put("id",7);
            rolePermissionsList.add(map7);
            Map<String,Object> map8 = new HashMap<>();
            map8.put("roleId",roleId);
            map8.put("id",8);
            rolePermissionsList.add(map8);
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
