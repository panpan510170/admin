package com.mshd.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * springBoot  手动获取service  接口注入
 *
 * Created by Pangaofeng on 2018/9/17
 */
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
