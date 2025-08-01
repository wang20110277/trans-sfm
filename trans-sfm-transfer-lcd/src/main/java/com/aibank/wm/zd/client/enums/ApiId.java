package com.aibank.wm.zd.client.enums;

/**
 *
 */
public enum ApiId {
    SEND_FILE("发送普通文件", "drcv.001.002"),
    SEND_NO_CHECK_FILE("发送不受检文件", "drcv.001.004"),
    RECEIVE_FILE("接收普通文件", "dprc.009.001"),
    RECEIVE_NO_CHECK_FILE("接收不受检文件", "drcv.009.004"),
    SEND_FILE_RESULT("发送结果", "dprc.009.002"),
    YES("", "1");

    public static ApiId getByCode(String code){
        for (ApiId value : ApiId.values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        return YES;
    }


    private String name;
    private String code;

    ApiId(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
