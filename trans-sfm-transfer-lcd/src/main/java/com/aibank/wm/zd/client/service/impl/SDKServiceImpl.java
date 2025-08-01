package com.aibank.wm.zd.client.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.aibank.wm.zd.client.constants.CommonConstant;
import com.aibank.wm.zd.client.constants.DataFieldConstant;
import com.aibank.wm.zd.client.controller.request.SendFileRequest;
import com.aibank.wm.zd.client.dao.MessageStore;
import com.aibank.wm.zd.client.enums.ApiId;
import com.aibank.wm.zd.client.enums.ReturnCodeEnum;
import com.aibank.wm.zd.client.pojo.ReceiveMessage;
import com.aibank.wm.zd.client.pojo.SendFileExchangeInfo;
import com.aibank.wm.zd.client.service.SDKService;
import com.cdc.gateway.sdk.main.GatewaySdk;
import com.cdc.gateway.sdk.main.model.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * @author libowen
 * @Package com.cdc.gateway.sdkDemo.service.impl
 * @description
 * @date 2021/10/19 9:14
 * @Version v1.0
 * @copyright ©2017-2021 中央结算公司，版权所有。
 */
@Service
@Slf4j
public class SDKServiceImpl implements SDKService {
    @Autowired
    private GatewaySdk sdk;


    @Override
    public Response sdkSend(SendFileRequest request) {
        Request req = new Request();

        Bodys bodys = req.getBodys();
        bodys.setHasAttachments(CommonConstant.HAS_ATTACHMENT_YES);
        bodys.setVersion(CommonConstant.VERSION);

        //文件信息
        final File zippedFile = ZipUtil.zip(request.getFileFullPath());
        Attachment attachment = Attachment.builder()
                .encrypted(true)
                .fileExtsInfo(request.getFileExtsInfo())
                .fileFullPath(zippedFile.getPath())
                .fileName(zippedFile.getName())
                .build();
        bodys.getAttachments().add(attachment);
        bodys.setTrnsType(ApiId.SEND_FILE.getCode());


        Header header = req.getHeader();
        header.setRequestSerialNo(RandomStringUtils.randomNumeric(30));
        header.setSigned(CommonConstant.HAS_SIGNED_YES);
        header.setEncrypted(CommonConstant.HAS_ENCRYPTED_YES);
        header.setRecvSysName(CommonConstant.RECEIVE_SYS_NAME_DRCV);
        header.setReceiver(CommonConstant.RECEIVE_SYS_NAME_DRCV);
        //接收业务服务的API号
        header.setApi(ApiId.SEND_FILE.getCode());
        header.setSendTime(new Date());

        //发送方机构
        final SendFileExchangeInfo sendFileExchangeInfo = new SendFileExchangeInfo();
        sendFileExchangeInfo.setSendTime(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        sendFileExchangeInfo.setSenderBusiSerialNo(request.getTransId());
        sendFileExchangeInfo.setSender(new SendFileExchangeInfo.Customer(request.getSenderCustId(), request.getSenderSubCustId()));

        //接收方,支持多个
        SendFileExchangeInfo.Receiver receiver = new SendFileExchangeInfo.Receiver();
        receiver.setDeliveryType(CommonConstant.DELIVERY_TYPE);
        receiver.setDeliveryTo(ImmutableList.of(new SendFileExchangeInfo.Customer(request.getDeliveryToCustId(), request.getDeliveryToSubCustId())));
        sendFileExchangeInfo.setReceiver(receiver);

        bodys.setBodyInExts(ImmutableMap.of(DataFieldConstant.exchangeInfo, sendFileExchangeInfo));
        req.setHeader(header);
        req.setBodys(bodys);

        Response rsp;
        try {
            log.info("发送文件请求报文 : {}", req);
            rsp = sdk.sender.send(req);
            log.info("发送文件返回报文 : {}", rsp);
            log.error("发送文件状态 code {},message {}", rsp.getBodys().getRtnCode(), rsp.getBodys().getRtnMsg());

        } catch (Exception e) {
            throw e;
        } finally {
            //删除原文件临时压缩文件
            FileUtil.del(zippedFile);
        }
        return rsp;
    }

    @Override
    public Response queryReceivedMessage(String serialNo) {
        log.info("查询文件请求 流水 : {}", serialNo);
        Response response = sdk.sender.queryMsgInfo(serialNo);
        log.info("查询文件返回报文 : {}", response);
        if (!ReturnCodeEnum.success.getCode().equals(response.getBodys().getRtnCode())) {
            log.error("查询文件返回失败 code {},message {}", response.getBodys().getRtnCode(), response.getBodys().getRtnMsg());
        }
        return response;
    }

    /**
     * @param serialNo
     * @param api
     */
    @SneakyThrows
    @Override
    public void handlerMessage(String serialNo, String api) {
        log.info("收到消息 serialNo: {} ,api: {}", serialNo, api);
        final BlockingQueue<ReceiveMessage> receiveMessageQueue = MessageStore.receiveMessageQueue;
        receiveMessageQueue.put(new ReceiveMessage(serialNo, api));
    }
}
