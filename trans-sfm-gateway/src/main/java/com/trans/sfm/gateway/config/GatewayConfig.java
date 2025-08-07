package com.trans.sfm.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    /**
     * 用户限流键解析器
     * 使用请求中的user-id头作为限流键
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            // 从请求头中获取user-id，如果不存在则使用默认值
            String userId = exchange.getRequest().getHeaders().getFirst("user-id");
            if (userId == null) {
                userId = "anonymous";
            }
            return Mono.just(userId);
        };
    }

    /**
     * IP限流键解析器
     * 使用请求的远程IP地址作为限流键
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest()
                .getRemoteAddress()
                .getAddress()
                .getHostAddress());
    }

    /**
     * 请求路径限流键解析器
     * 使用请求路径作为限流键
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }
}