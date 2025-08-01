package com.aibank.wm.zd.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "system-config")
@Data
public class SystemConfigProperties {
    private Integer sendFileCacheDays = 1;
    private Integer maxSendRetriedCount = 5;
}
