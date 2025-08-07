package com.trans.sfm.ta.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TaOnlineApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(TaOnlineApplication.class);

    public static void main(String[] args) {
        logger.info("Starting trans-sfm-ta-online application...");
        SpringApplication.run(TaOnlineApplication.class, args);
        logger.info("trans-sfm-ta-online application started successfully.");
    }
}