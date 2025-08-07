package com.trans.sfm.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    // 需要鉴权的路径前缀
    private static final List<String> AUTH_WHITELIST = Arrays.asList(
            "/api/account/openPersonAccount"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取请求路径
        String path = request.getPath().toString();

        // 检查是否需要鉴权
        if (needsAuth(path)) {
            // 从请求头获取认证信息
            String authHeader = request.getHeaders().getFirst("Authorization");
            String apiKey = request.getHeaders().getFirst("X-API-Key");

            // 简单的鉴权检查
            if (authHeader == null || apiKey == null) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            // 可以添加更复杂的鉴权逻辑
            // 例如验证token、API Key的有效性等
        }

        return chain.filter(exchange);
    }

    /**
     * 判断路径是否需要鉴权
     *
     * @param path 请求路径
     * @return 是否需要鉴权
     */
    private boolean needsAuth(String path) {
        return AUTH_WHITELIST.stream().anyMatch(path::startsWith);
    }

    @Override
    public int getOrder() {
        return 0; // 设置过滤器顺序，数字越小优先级越高
    }
}