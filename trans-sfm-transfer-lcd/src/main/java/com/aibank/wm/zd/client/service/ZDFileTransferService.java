package com.aibank.wm.zd.client.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONUtil;
import com.aibank.wm.zd.client.config.FilePathConfigProperties;
import com.aibank.wm.zd.client.config.OrganizationConfigProperties;
import com.aibank.wm.zd.client.constants.CommonConstant;
import com.aibank.wm.zd.client.controller.request.SendFileRequest;
import com.aibank.wm.zd.client.enums.ApiId;
import com.aibank.wm.zd.client.enums.LoggerCode;
import com.aibank.wm.zd.client.enums.ReturnCodeEnum;
import com.aibank.wm.zd.client.pojo.FileNameVariables;
import com.aibank.wm.zd.client.pojo.ReceiveMessage;
import com.aibank.wm.zd.client.util.DateUtils;
import com.aibank.wm.zd.client.util.FileUtils;
import com.aibank.wm.zd.client.util.FileNameUtil;
import com.alibaba.fastjson.JSON;
import com.cdc.gateway.sdk.main.model.Attachment;
import com.cdc.gateway.sdk.main.model.Response;
import com.chinabond.fsp.common.digest.FileMd5Util;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import static com.aibank.wm.zd.client.constants.CommonConstant.OK_FILE_SUFFIX;

@Service
@Slf4j
public class ZDFileTransferService {

    @Autowired
    private FilePathConfigProperties filePathConfigProperties;

    @Autowired
    private OrganizationConfigProperties organizationConfigProperties;

    @Autowired
    private SDKService sdkService;

    public void sendFile(String file) {
        final File originFile = FileUtil.file(file);
        if (!originFile.exists()) {
            throw new IllegalArgumentException("发送文件不存在 " + file);
        }
        FileNameVariables fileNameVariables = FileNameUtil.extractVariables(originFile.getName());
        if (fileNameVariables == null) {
            throw new IllegalArgumentException("文件名称不合法 " + file);
        }
        log.info("提取文件名参数 {}", JSON.toJSONString(fileNameVariables));
        final OrganizationConfigProperties.Organization sendOrganization = organizationConfigProperties.getMe();
        if (!sendOrganization.getOrganizationCode().equals(fileNameVariables.getSendOrganization())) {
            throw new IllegalArgumentException("文件名称不合法 ,发送机构不正确" + file);
        }

        final OrganizationConfigProperties.Organization receiveOrganization = organizationConfigProperties.getByOrganizationCode(fileNameVariables.getReceiveOrganization());
        if (receiveOrganization == null) {
            throw new IllegalArgumentException("文件名称不合法 ,接收机构未配置" + file);
        }

        final String fileHash = FileMd5Util.getMD5(originFile);
        SendFileRequest sendFileRequest = new SendFileRequest();
        sendFileRequest.setTransId("BX" + RandomUtil.randomNumbers(18));
        sendFileRequest.setFileFullPath(originFile.getPath());
        sendFileRequest.setFileExtsInfo(fileNameVariables.getFileType());
        sendFileRequest.setSenderCustId(sendOrganization.getOrganizationId());
        sendFileRequest.setSenderSubCustId(sendOrganization.getSubOrganizationId());
        sendFileRequest.setDeliveryToCustId(receiveOrganization.getOrganizationId());
        sendFileRequest.setDeliveryToSubCustId(receiveOrganization.getSubOrganizationId());
        sendFileRequest.setFileHash(fileHash);

        try {
            final Response response = sdkService.sdkSend(sendFileRequest);
            if (ReturnCodeEnum.success.getCode().equals(response.getBodys().getRtnCode())) {
                log.info("{} 发送成功", file);
                fileNameVariables.setDate(DateUtils.getYearMonthDay(DateUtils.getCurrentDateTime()));
                final String bakFile = FileNameUtil.stringSubstitute(filePathConfigProperties.getZd().getSendBakFile(), fileNameVariables);
                FileUtils.moveWithSignalFile(originFile, FileUtil.file(bakFile), OK_FILE_SUFFIX);
                log.info("{} 文件移动到发送成功目录", file);
            }
        } catch (Exception e) {
            log.info("{} {} 发送失败 ",LoggerCode.LCD_ERROR, file, e);
        }

    }


    public void receiveFile(ReceiveMessage message) {
        String serialNo = message.getSerialNo();
        final Response response = sdkService.queryReceivedMessage(serialNo);
        log.error("查询消息返回{}", JSON.toJSON(response));

        if (!ReturnCodeEnum.success.getCode().equals(response.getBodys().getRtnCode())) {
            log.error("{} 查询接收文件返回失败 code {},message {}", LoggerCode.LCD_ERROR, response.getBodys().getRtnCode(), response.getBodys().getRtnMsg());
        }

        Object fdiErrMsg = JSONUtil.getByPath(JSONUtil.parse(response.getBodys()), "bodyInExts.exchangeInfo.fdiErrMsg");
        if (fdiErrMsg != null) {
            log.info("{} 收到错误文件回执消息: {}", LoggerCode.ERROR_RECEIPT_MESSAGE, fdiErrMsg);
        }
        //有附件信息
        if (!CommonConstant.HAS_ATTACHMENT_YES.equals(response.getBodys().getHasAttachments())) {
            log.info("{} 没有附件", serialNo);
            return;
        }
        log.info("接收到的附件信息---------------------------------------");
        for (Attachment attachment : response.getBodys().getAttachments()) {
            File filepath = ZipUtil.unzip((attachment.getFileFullPath()));
            final File[] files = filepath.listFiles();
            for (File file : files) {
                final boolean match = FileNameUtil.match(file.getName());
                if (match) {
                    receiveFile(message, file);
                } else {
                    receiptFile(file);
                }
            }
        }
        log.info("接收到的附件信息---------------------------------------");
    }

    private void receiptFile(File file) {
        List<String> lines = FileUtils.readLines(new File(file.getPath()), Charset.forName("GBK"), 100);
        log.info("{} 接收到文件类型为发送结果文件,路径： {}，内容 : {} ", LoggerCode.ERROR_RECEIPT_MESSAGE, file.getPath(), lines);
        final ImmutableMap<String, Object> extractVariables = ImmutableMap.of("date", DateUtils.getYearMonthDay(DateUtils.getCurrentDateTime()));
        for (String path : filePathConfigProperties.getZd().getReceipt()) {
            final String sendFilePath = FileNameUtil.stringSubstitute(path, extractVariables);
            FileUtils.copyWithSignalFile(file, sendFilePath, OK_FILE_SUFFIX);
        }
    }

    private void receiveFile(ReceiveMessage message, File file) {
        final FileNameVariables fileNameVariables = FileNameUtil.extractVariables(file.getName());
        if (fileNameVariables == null) {
            log.info("{} 接收到文件类型未知,{}", LoggerCode.UNKNOWN_RECEIVE_FILE, fileNameVariables);
            return;
        }
        fileNameVariables.setDate(DateUtils.getYearMonthDay(DateUtils.getCurrentDateTime()));

        final OrganizationConfigProperties.Organization sendOrganization = organizationConfigProperties.getByOrganizationCode(fileNameVariables.getSendOrganization());
        if (sendOrganization == null) {
            throw new IllegalArgumentException("发送机构未配置" + fileNameVariables);
        }
        if (ApiId.RECEIVE_NO_CHECK_FILE.getCode().equals(message.getApi())) {
            log.info("接收到文件类型为不受检文件 {}", file.getPath());
        }
        if (ApiId.RECEIVE_NO_CHECK_FILE.getCode().equals(message.getApi()) && !sendOrganization.isAllowNoCheck()) {
            log.info("接收到文件类型为不受检文件,但是机构没配置可接受此文件，忽略 {}", file.getPath());
            return;
        }
        final String receiveFile = FileNameUtil.stringSubstitute(filePathConfigProperties.getZd().getReceiveFile(), fileNameVariables);
        FileUtils.copyWithSignalFile(file, FileUtil.file(receiveFile), OK_FILE_SUFFIX);
        log.info("{} 文件移动到接收目录 {} ", file.getPath(), receiveFile);
    }
}
