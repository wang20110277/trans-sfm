package com.trans.sfm.sa.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.trans.sfm.sa.online")
public class Application {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting trans-sfm-sa-online application...");
        SpringApplication.run(Application.class, args);
        logger.info("trans-sfm-sa-online application started successfully.");
    }
}