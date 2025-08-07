package com.trans.sfm.sa.online.domain;

/**
 * 请求基类
 */
public class BaseRequest {
    
    /**
     * 版本号
     */
    private String version;
    
    /**
     * 交易码
     */
    private String messageCode;
    
    /**
     * 业务合作伙伴 ID
     */
    private String partnerCode;
    
    /**
     * 业务合作伙伴渠道
     */
    private String partnerChannel;
    
    /**
     * 合作方客户编号
     */
    private String partnerCustomerId;
    
    /**
     * 报文发起时间
     */
    private String requestTime;
    
    /**
     * 请求报文 ID
     */
    private String requestMsgId;
    
    /**
     * 保留字段
     */
    private String reserve;

    // Getter和Setter方法
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerChannel() {
        return partnerChannel;
    }

    public void setPartnerChannel(String partnerChannel) {
        this.partnerChannel = partnerChannel;
    }

    public String getPartnerCustomerId() {
        return partnerCustomerId;
    }

    public void setPartnerCustomerId(String partnerCustomerId) {
        this.partnerCustomerId = partnerCustomerId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMsgId() {
        return requestMsgId;
    }

    public void setRequestMsgId(String requestMsgId) {
        this.requestMsgId = requestMsgId;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}