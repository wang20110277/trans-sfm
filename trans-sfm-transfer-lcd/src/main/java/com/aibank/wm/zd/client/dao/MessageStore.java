package com.aibank.wm.zd.client.dao;

import com.aibank.wm.zd.client.pojo.ReceiveMessage;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author tongjie
 * @date 2024/1/22 17:26
 * @description
 */
public class MessageStore {
    //为了去掉hazelcast 临时用的
    public static final BlockingQueue<ReceiveMessage> receiveMessageQueue = new LinkedBlockingDeque<>();
    public static final Cache<String, String> sendFileMessage = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

}
