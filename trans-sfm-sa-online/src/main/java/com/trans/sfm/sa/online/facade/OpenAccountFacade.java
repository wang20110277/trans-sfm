package com.trans.sfm.sa.online.facade;

import com.trans.sfm.sa.online.api.OpenAccountAPI;
import com.trans.sfm.sa.online.domain.OpenAccountRequest;
import com.trans.sfm.sa.online.domain.OpenAccountResponse;
import org.springframework.stereotype.Component;

/**
 * 开户接口实现类
 */
@Component
public class OpenAccountFacade implements OpenAccountAPI {
    
    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest request) {
        // 实际业务逻辑应该在这里实现
        OpenAccountResponse response = new OpenAccountResponse();
        response.setTaClient("test_ta_client");
        response.setAssetAcc("test_asset_acc");
        response.setExtData("test_ext_data");
        response.setSuccess(true);
        response.setMessage("开户成功");
        return response;
    }
}