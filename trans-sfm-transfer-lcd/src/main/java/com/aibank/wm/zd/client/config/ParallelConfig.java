package com.aibank.wm.zd.client.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.*;

@Configuration
@Slf4j
public class ParallelConfig {
    @Bean
    public ConcurrentTaskScheduler receiveMessageExecutor() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10,
                new TaskThreadFactory("task-ThreadPool-", false, Thread.MAX_PRIORITY),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler(scheduledExecutorService);
        taskScheduler.setErrorHandler((throwable) -> log.error("receiveMessageExecutor 执行异常", throwable));
        return taskScheduler;
    }
}
