package com.aibank.wm.zd.client.schedule;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class ConfigAutoRefreshSchedule {

    @Autowired
    private ConfigDataContextRefresher configDataContextRefresher;

    @SneakyThrows
    @Ignore
    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void refreshConfig() {
        log.info("execute task refreshConfig");
        final Set<String> refresh = configDataContextRefresher.refresh();
        log.info("刷新配置 {} ", refresh);
        log.info("execute task refreshConfig end");
    }
}
