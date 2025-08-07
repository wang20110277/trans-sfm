package com.trans.sfm.sa.online.api;

import com.trans.sfm.sa.online.domain.OpenAccountRequest;
import com.trans.sfm.sa.online.domain.OpenAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 开户接口API
 */
@FeignClient(name = "open-account-service")
@RequestMapping("/sa/api/account")
public interface OpenPersonAccountAPI {
    
    /**
     * 开户接口
     * @param request 开户请求参数
     * @return 开户结果
     */
    @PostMapping("/openPersonAccount")
    OpenAccountResponse openPersonAccount(@RequestBody OpenAccountRequest request);
}