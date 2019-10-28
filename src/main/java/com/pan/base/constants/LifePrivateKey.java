package com.pan.base.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 生活apikey 私钥
 * @author pan
 * @date 2019/10/23 18:21
 */
@Data
@Component
@ConfigurationProperties(prefix = "admin.lift")
public class LifePrivateKey {
    //状态 0代表成功
    private int status;
    //彩票
    private String lottery;
    //笑话
    private String joke;
    // QQ号码测吉凶
    private String qqResult;
    // 周公解梦
    private String dream;
    // 老黄历
    private String laohuangli;
    // 天气预报
    private String simpleWeather;

}
