package com.aibank.wm.zd.client.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SendFileResponse {
    String rtnCode;
    String rtnMsg;
    private String transId;
}
