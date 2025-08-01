package com.aibank.wm.zd.client.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiveMessage{
    private String serialNo;
    private String api;

    /**
     * 重试次数
     */
    private int processTimes;

    public ReceiveMessage(String serialNo, String api) {
        this.serialNo = serialNo;
        this.api = api;
    }

}
