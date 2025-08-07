package com.trans.sfm.sa.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.trans.sfm.sa.online")
public class SaOnlineApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(SaOnlineApplication.class);

    public static void main(String[] args) {
        logger.info("Starting trans-sfm-sa-online application...");
        SpringApplication.run(SaOnlineApplication.class, args);
        logger.info("trans-sfm-sa-online application started successfully.");
    }
}