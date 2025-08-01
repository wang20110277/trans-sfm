package com.aibank.wm.zd.client.service.impl;

import com.aibank.wm.zd.client.enums.ApiId;
import com.aibank.wm.zd.client.enums.LoggerCode;
import com.aibank.wm.zd.client.service.SDKService;
import com.cdc.gateway.sdk.main.ReceiveCallBack;
import com.cdc.gateway.sdk.main.model.RecvRsp;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author libowen
 * @Package com.cdc.gateway.sdkDemo.service.impl
 * @description
 * @date 2021/10/21 9:12
 * @Version v1.0
 * @copyright ©2017-2021 中央结算公司，版权所有。
 */
@Slf4j
@Service
public class ReceiveCallBackImpl implements ReceiveCallBack {
    @Autowired
    private SDKService sdkService;

    @Override
    public RecvRsp onReceive(String serialNo, String api) {
        ApiId apiId = ApiId.getByCode(api);
        log.info("{} onReceive api : {} ,type : {}, serialNo {}", LoggerCode.RECEIVE_MESSAGE, api, apiId.getName(),serialNo);
        try {
            //@TEST 收到文件 sdkService.handlerMessage("202112270000164571", api);
            sdkService.handlerMessage(serialNo, api);
        } catch (Exception e) {
            log.error("处理下发消息失败", e);
            return RecvRsp.builder().rtnCode("-1").rtnMsg("客户系统保存失败").build();
        }
        return RecvRsp.builder().build();
    }

    @Override
    public void onException(Exception e) {
        //业务系统若出现错误则会回调本方法传回异常信息，可根据异常信息码查询文档处理异常
        log.error("SDK传递的异常", e);
    }
}
