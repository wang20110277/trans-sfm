package com.trans.sfm.sa.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaBatchApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(SaBatchApplication.class);

    public static void main(String[] args) {
        logger.info("Starting trans-sfm-sa-batch application...");
        SpringApplication.run(SaBatchApplication.class, args);
        logger.info("trans-sfm-sa-batch application started successfully.");
    }
}