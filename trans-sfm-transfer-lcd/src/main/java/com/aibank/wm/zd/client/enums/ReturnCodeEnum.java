package com.aibank.wm.zd.client.enums;

public enum ReturnCodeEnum {

    success("ISCC", "成功");

    private String code;
    private String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
