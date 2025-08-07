package com.trans.sfm.sa.online.domain;

/**
 * 基础响应类，定义所有对外接口统一的响应结构
 */
public class BaseResponse {
    
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
     * 报文响应时间
     */
    private String responseTime;
    
    /**
     * 请求报文 ID
     */
    private String requestMsgId;
    
    /**
     * 保留字段
     */
    private String reserve;
    
    /**
     * 交易流水号
     */
    private String transId;
    
    /**
     * 交易状态 U : 处理中 S : 成功 F : 失败
     */
    private String returnStatus;
    
    /**
     * 响应码 成功为00000000
     * 处理中为00000001
     * 其他情况为失败
     * 通用：
     * TATSOFL0000，Throwable系统异常
     * TATSOFL0001，Exception未知异常
     * SOFL0001，未经授权
     * SOFL0002，参数非法
     * SOFL0007，服务策略不存在
     * SOFL0010，联机调用失败(SOF-BSF调用异常)
     * SOFL0014，客户信息冲突(机构客户号不唯一)
     */
    private String returnCode;
    
    /**
     * 响应消息
     */
    private String returnMessage;

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

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}