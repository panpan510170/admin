package com.mshd.config.interceptor;

import com.mshd.serivce.UserService;
import com.mshd.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 防止表单重复提交
 *
 * Created by Pangaofeng on 2018/9/6
 */
@Component
public class PreDupSubmissionsIntercetor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 处理请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Access-Token");

        if(!"".equals(token) && null != token){
            if(stringRedisTemplate.hasKey(RedisKey.preDupSubmissions + token)){
                stringRedisTemplate.opsForValue().set(RedisKey.preDupSubmissions + token,"1",5, TimeUnit.SECONDS);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
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
        String token = request.getHeader("Access-Token");
        stringRedisTemplate.delete(RedisKey.preDupSubmissions + token);
    }

}
