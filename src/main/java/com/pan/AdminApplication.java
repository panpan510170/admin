package com.pan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.pan.dao.mapper*")
@EntityScan("com.pan.model.entitys")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    /**
     * 处理乱码问题
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 处理jsp无法访问问题
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
