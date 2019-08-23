package com.pan.base.util;

import com.pan.model.entitys.system.SUser;
import org.apache.shiro.SecurityUtils;

/**
 * 提供一些CMS系统中使用到的共用方法,比如获得会员信息,获得后台站点信息
 * @author pan
 * @date 2019/8/23 18:17
 */
public class MyUtils {

    /**
     * 获取登录用户信息
     * @return
     */
    public static SUser getUserInfo(){
        return (SUser)SecurityUtils.getSubject().getPrincipal();
    }
}
