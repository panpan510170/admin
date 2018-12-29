package com.mshd.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 解决跨域请求
 *
 * Created by Pangaofeng on 2018/9/6
 */
public class RequestInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    /**
     * 处理请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");

        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Access-Source");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Expose-Headers", "*");

        return true;

    }
    /**
     * 处理请求后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 渲染完视图之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
