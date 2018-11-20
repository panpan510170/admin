package com.mshd.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pangaofeng on 2018/9/6
 *
 * WebMvcConfigurerAdapter  此方法已过时
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private static final List<String> tokenExcludeUrlList = new ArrayList<>();

    static {
        tokenExcludeUrlList.add("/login/login");//登录
        tokenExcludeUrlList.add("/regist/regist");//注册
        tokenExcludeUrlList.add("/user/userNameOnly");//用户名唯一效验
        tokenExcludeUrlList.add("/swagger-resources/**");//接口页面-静态资源
        tokenExcludeUrlList.add("/swagger-ui.html/**");//接口页面
        tokenExcludeUrlList.add("/webjars/**");//接口页面-静态资源
        tokenExcludeUrlList.add("/v2/**");//接口页面-静态资源
    }

    //若要将拦截器注入service,关键，将拦截器作为bean写入配置中
    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }


    /**
     * 拦截器链
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**").excludePathPatterns(tokenExcludeUrlList);
        super.addInterceptors(registry);
    }
    /**
     * 放行访问静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
