package com.mshd.config.interceptor;

import com.mshd.enums.ResultCodeEnum;
import com.mshd.enums.UserFromTypeEnum;
import com.mshd.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,userFromType");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Expose-Headers", "*");

        /**
         * 判断来源用户
         */
        /*HttpSession session = request.getSession();
        if (UserFromTypeEnum.app.getId().equals(request.getHeader("userFromType"))) {
            if (session.getAttribute("user") != null) return true;
        } else if (UserFromTypeEnum.system.getId().equals(request.getHeader("userFromType"))) {
            if (session.getAttribute("systemUser") != null) return true;
        }

        ResponseUtils.doReturn(ResultCodeEnum.sessionExpire,response);*/
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
