package com.trans.sfm.client.batch.batch;

import com.trans.sfm.client.batch.entity.User;
import com.trans.sfm.client.batch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private UserService userService;

    @Bean
    public ItemReader<User> reader() {
        logger.info("Creating user data reader");
        List<User> users = Arrays.asList(
                new User(),
                new User()
        );
        
        users.get(0).setName("John Doe");
        users.get(0).setAge(30);
        users.get(0).setEmail("john.doe@example.com");
        
        users.get(1).setName("Jane Smith");
        users.get(1).setAge(25);
        users.get(1).setEmail("jane.smith@example.com");
        
        return new ListItemReader<>(users);
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        logger.info("Creating user data processor");
        return user -> {
            // Process user data if needed
            logger.debug("Processing user: {}", user.getName());
            return user;
        };
    }

    @Bean
    public ItemWriter<User> writer() {
        logger.info("Creating user data writer");
        return users -> {
            // Save users to database
            logger.info("Writing {} users to database", users.size());
            userService.saveBatch((List<User>) users);
            users.forEach(user -> logger.debug("Saved user: {}", user.getName()));
        };
    }

    @Bean
    public Step step1() {
        logger.info("Creating step1");
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job importUserJob() {
        logger.info("Creating importUserJob");
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}