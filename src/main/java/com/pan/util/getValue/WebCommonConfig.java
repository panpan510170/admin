package com.pan.util.getValue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by Pangaofeng on 2018/9/22
 */
@PropertySources(value = {@PropertySource("classpath:/application-${spring.profiles.active}.yml")})
@Component("webCommonConfig")
public class WebCommonConfig implements IPropertyConfig {
    @Resource
    Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Override
    public String setProperty(String key) {
        if (null == key) {
            return null;
        }
        if (env.getProperty(key) == null) {
            return null;
        }try {
            return new String(env.getProperty(key).getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            return env.getProperty(key);
        }
    }
}
