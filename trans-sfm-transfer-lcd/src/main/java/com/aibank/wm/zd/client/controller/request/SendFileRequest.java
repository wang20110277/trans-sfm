package com.aibank.wm.zd.client.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SendFileRequest {


    private String transId;

    /**
     * 文件路径
     */
    private String fileFullPath;

    /**
     * 文件类型 ,比如 001
     */
    private String fileExtsInfo;

    /**
     * 发送机构号
     */
    private String senderCustId;
    /**
     * 发送子机构号
     */
    private String senderSubCustId;

    /**
     * 接收机构号
     */
    private String deliveryToCustId;

    /**
     * 接收子机构号
     */
    private String deliveryToSubCustId;

    /**
     * 文件 hash
     */
    private String fileHash;

}
