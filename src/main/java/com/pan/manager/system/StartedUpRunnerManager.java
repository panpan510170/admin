package com.pan.manager.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author pan
 * @date 2019/8/26 16:19
 */
@Component
@Order(99)
public class StartedUpRunnerManager implements ApplicationRunner {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ConfigurableApplicationContext context;
    //@Autowired
    //private FebsProperties febsProperties;
    //@Autowired
    //private IRedisService redisService;

    @Value("${server.port:8888}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*try {
            // 测试 Redis连接是否正常
            redisService.exists("febs_test");
        } catch (Exception e) {
            logger.error(" ____   __    _   _ ");
            logger.error("| |_   / /\\  | | | |");
            logger.error("|_|   /_/--\\ |_| |_|__");
            logger.error("                        ");
            logger.error("FEBS启动失败，{}", e.getMessage());
            logger.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            // 关闭 FEBS
            context.close();
        }*/
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = "/login";
            if (StringUtils.isNotBlank(contextPath))
                url += contextPath;
            if (StringUtils.isNotBlank(loginUrl))
                url += loginUrl;
            logger.info(" __    ___   _      ___   _     ____ _____  ____ ");
            logger.info("/ /`  / / \\ | |\\/| | |_) | |   | |_   | |  | |_  ");
            logger.info("\\_\\_, \\_\\_/ |_|  | |_|   |_|__ |_|__  |_|  |_|__ ");
            logger.info("                                                      ");
            logger.info("后台管理系统启动完毕，地址：{}", url);
            logger.info("用户名：admin,密码:admin");
        }
    }
}
