package com.aibank.wm.zd.client.schedule;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
@Slf4j
public class ScheduleAspect {


    /**
     *
     * @param joinPoint
     * @param scheduled
     * @return
     * @throws Throwable
     */
    @Around("@annotation(scheduled))")
    public Object jobLockCheck(ProceedingJoinPoint joinPoint, Scheduled scheduled) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        log.info("job 耗时统计, 接口 {} ,耗时 {} ms", joinPoint.getSignature().getName(), stopWatch.getLastTaskTimeMillis());
        return proceed;
    }

}
