package com.aibank.wm.zd.client.dao;


public interface ReceiveMessageDao {

    void push(String serialNo, String api);

    void delete(String serialNo);

    String peek();
}
