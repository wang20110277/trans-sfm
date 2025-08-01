package com.aibank.wm.zd.client.enums;

public enum LoggerCode {

    RECEIVE_MESSAGE("BBLLCDIF001", "收到消息"),

    ERROR_RECEIPT_MESSAGE("BBLLCDER001", "收到错误文件回执"),
    UNKNOWN_SEND_FILE("BBLLCDER002", "发送文件未知文件名"),
    UNKNOWN_RECEIVE_FILE("BBLLCDER003", "接受文件未知文件名"),

    UNKNOWN_ERROR("TATLCDER001", "未知异常"),
    LCD_ERROR("TATLCDER002", "理财登相关错误"),
    ;


    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    LoggerCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code;
    }
}
