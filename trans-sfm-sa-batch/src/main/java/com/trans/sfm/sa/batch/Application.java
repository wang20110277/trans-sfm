package com.trans.sfm.sa.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting trans-sfm-sa-batch application...");
        SpringApplication.run(Application.class, args);
        logger.info("trans-sfm-sa-batch application started successfully.");
    }
}