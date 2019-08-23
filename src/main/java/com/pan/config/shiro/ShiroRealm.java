package com.pan.config.shiro;

import com.pan.base.handler.DataHandler;
import com.pan.model.entitys.system.SPermissions;
import com.pan.model.entitys.system.SUser;
import com.pan.serivce.SystemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 * @author pan
 * @date 2019/8/2 11:28
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SystemService systemService;

    /**
     * 用户认证
     *
     * @param token AuthenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String userName = (String) token.getPrincipal();
        // 从数据库获取对应用户名密码的用户
        SUser user = systemService.getUserByUserName(userName);
        String password = user.getPassword();
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权模块，获取用户角色和权限
     *
     * @param principal principal
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("————授权方法————");
        SUser user = (SUser) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();
        //查询用户角色
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String role = systemService.getRoleByUserName(userName);
        Set<String> roleSet = new HashSet<>();
        roleSet.add(role);
        simpleAuthorizationInfo.setRoles(roleSet);
        //查询用户权限
        Set<String> permissionSet = new HashSet<>();
        try {
            List<SPermissions> list = systemService.getUserPermissionsAllList(user.getId());
            //permissionSet = list.stream().map(SPermissions::getPermissions).collect(Collectors.toSet());
            for(SPermissions s : list){
                if(DataHandler.isNotEmpty(s.getPermissions())){
                    permissionSet.add(s.getPermissions());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
