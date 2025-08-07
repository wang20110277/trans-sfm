package com.trans.sfm.sa.online.facade;

import com.trans.sfm.sa.online.api.OpenPersonAccountAPI;
import com.trans.sfm.sa.online.domain.OpenAccountRequest;
import com.trans.sfm.sa.online.domain.OpenAccountResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开户接口实现类
 */
@RestController
public class OpenPersonAccountFacade implements OpenPersonAccountAPI {
    
    @Override
    public OpenAccountResponse openPersonAccount(OpenAccountRequest request) {
        // 实际业务逻辑应该在这里实现
        OpenAccountResponse response = new OpenAccountResponse();
        response.setTaClient("test_ta_client");
        response.setAssetAcc("test_asset_acc");
        response.setExtData("test_ext_data");
        response.setReturnStatus("S");
        response.setReturnCode("00000000");
        response.setReturnMessage("开户成功");
        return response;
    }
}