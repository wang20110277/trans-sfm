package com.trans.sfm.sa.online.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 开户接口API
 */
@FeignClient(name = "open-account-service")
public interface OpenAccountAPI {
    
    /**
     * 开户接口
     * @param request 开户请求参数
     * @return 开户结果
     */
    @PostMapping("/open")
    Map<String, Object> openAccount(@RequestBody Map<String, Object> request);
}