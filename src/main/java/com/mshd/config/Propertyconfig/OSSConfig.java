package com.mshd.config.Propertyconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSConfig {

    private String AccessKeyID;

    private String AccessKeySecret;

    private String endpoint;

}
