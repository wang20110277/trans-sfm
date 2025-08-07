package com.trans.sfm.client.online.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    
    @Value("${gateway.url}")
    private String gatewayUrl;
    
    private final RestTemplate restTemplate;
    
    public ClientController() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * 处理来自ESB的请求并转发到网关
     * 
     * @param requestBody 请求体
     * @return 响应结果
     */
    @PostMapping("/forward")
    public ResponseEntity<String> forwardToGateway(@RequestBody Map<String, Object> requestBody) {
        try {
            // 转发请求到网关
            String url = gatewayUrl + "/api/forward";
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error forwarding request: " + e.getMessage());
        }
    }
    
    /**
     * 专门用于开户的接口
     * 
     * @param requestBody 开户请求参数
     * @return 开户结果
     */
    @PostMapping("/openAccount")
    public ResponseEntity<String> openAccount(@RequestBody Map<String, Object> requestBody) {
        try {
            // 转发开户请求到网关
            String url = gatewayUrl + "/api/account/openPersonAccount";
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing account opening request: " + e.getMessage());
        }
    }
    
    /**
     * 健康检查接口
     * 
     * @return 健康状态
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Client online service is running");
    }
}