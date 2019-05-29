package com.pan.config.interceptor;

import com.pan.enums.ResultCodeEnum;
import com.pan.enums.UserSourceEnum;
import com.pan.model.*;
import com.pan.serivce.SystemService;
import com.pan.serivce.UserService;
import com.pan.util.JwtUtils;
import com.pan.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 管理token
 *
 * Created by Pangaofeng on 2018/9/11
 *
 * 如果拦截器里面要注入service  就必须加注解@Component
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private SystemService systemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Access-Token");
        String source = request.getHeader("Access-Source");
        int sourceInt = source == null ? 0 : Integer.parseInt(source);
        //前台
        if(null != source && null != token && UserSourceEnum.front.getId() == sourceInt){
            TUser user = JwtUtils.decode(token, TUser.class);
            if(null == user) {
                ResponseUtils.doReturn(ResultCodeEnum.tokenError, response);
                return false;
            }
            TUserToken tUserToken = userService.selectTokenByUserId(user.getId());
            if(null == user || null == tUserToken || !tUserToken.getToken().equals(token)) {
                ResponseUtils.doReturn(ResultCodeEnum.tokenError, response);
                return false;
            }else{
                request.setAttribute("user",user);
                return true;
            }
        }

        //后台其他
        if(null != source && null != token && UserSourceEnum.behind.getId() == sourceInt){
            SUser user = JwtUtils.decode(token, SUser.class);
            if(null == user) {
                ResponseUtils.doReturn(ResultCodeEnum.tokenError, response);
                return false;
            }
            SUserToken sUserToken = systemService.selectTokenByUserId(user.getId());
            if(null == user || null == sUserToken || !sUserToken.getToken().equals(token)) {
                ResponseUtils.doReturn(ResultCodeEnum.tokenError, response);
                return false;
            }else{
                request.setAttribute("suser",user);
                return true;
            }
        }
        ResponseUtils.doReturn(ResultCodeEnum.tokenError, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
