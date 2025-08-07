package com.trans.sfm.sa.online.domain;

/**
 * 开户响应类
 */
public class OpenAccountResponse extends BaseResponse {
    
    /**
     * 交易账号
     */
    private String taClient;
    
    /**
     * 理财账号
     */
    private String assetAcc;
    
    /**
     * 扩展字段
     */
    private String extData;
    
    // 构造方法
    public OpenAccountResponse() {
    }
    
    // 静态方法，快速构建成功或失败响应
    public static OpenAccountResponse success() {
        OpenAccountResponse response = new OpenAccountResponse();
        response.setReturnStatus("S");
        response.setReturnCode("00000000");
        response.setReturnMessage("操作成功");
        return response;
    }
    
    public static OpenAccountResponse processing() {
        OpenAccountResponse response = new OpenAccountResponse();
        response.setReturnStatus("U");
        response.setReturnCode("00000001");
        response.setReturnMessage("处理中");
        return response;
    }
    
    public static OpenAccountResponse failure(String returnCode, String returnMessage) {
        OpenAccountResponse response = new OpenAccountResponse();
        response.setReturnStatus("F");
        response.setReturnCode(returnCode);
        response.setReturnMessage(returnMessage);
        return response;
    }
    
    // 支持链式调用添加数据
    public OpenAccountResponse addData(String key, Object value) {
        // 可以根据需要实现
        return this;
    }
    
    // Getter和Setter方法
    
    public String getTaClient() {
        return taClient;
    }
    
    public void setTaClient(String taClient) {
        this.taClient = taClient;
    }
    
    public String getAssetAcc() {
        return assetAcc;
    }
    
    public void setAssetAcc(String assetAcc) {
        this.assetAcc = assetAcc;
    }
    
    public String getExtData() {
        return extData;
    }
    
    public void setExtData(String extData) {
        this.extData = extData;
    }
}