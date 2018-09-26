package com.mshd.config.interceptor;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.util.JwtUtils;
import com.mshd.util.ResponseUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mshd.model.User;

/**
 * @Description: 管理token
 *
 * Created by Pangaofeng on 2018/9/11
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Access-Token");
        User user = JwtUtils.decode(token, User.class);
        if(null != user){
            request.setAttribute("user",user);
            return true;
        }
        ResponseUtils.doReturn(ResultCodeEnum.tokenError,response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
