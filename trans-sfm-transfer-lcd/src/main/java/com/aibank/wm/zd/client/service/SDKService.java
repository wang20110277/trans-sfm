package com.aibank.wm.zd.client.service;

import com.aibank.wm.zd.client.controller.response.SendFileResponse;
import com.cdc.gateway.sdk.main.model.Response;
import com.aibank.wm.zd.client.controller.request.SendFileRequest;

/**
 * @author libowen
 * @Package com.cdc.gateway.sdkDemo.service
 * @description
 * @date 2021/10/19 9:14
 * @Version v1.0
 * @copyright ©2017-2021 中央结算公司，版权所有。
 */
public interface SDKService {
    /**
     * SDK发送
     */
    Response sdkSend(SendFileRequest request);

    /**
     * 根据流水号获取信息
     * @param serialNo
     */
    Response queryReceivedMessage(String serialNo);
    
    void handlerMessage(String serialNo, String api);
    
}
