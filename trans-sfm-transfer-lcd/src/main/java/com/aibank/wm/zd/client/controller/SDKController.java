package com.aibank.wm.zd.client.controller;

import com.aibank.wm.zd.client.config.FilePathConfigProperties;
import com.aibank.wm.zd.client.controller.request.SendFileRequest;
import com.aibank.wm.zd.client.service.SDKService;
import com.cdc.gateway.sdk.main.model.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author libowen
 * @Package com.cdc.gateway.sdkDemo.controller
 * @description
 * @date 2021/10/18 15:44
 * @Version v1.0
 * @copyright ©2017-2021 中央结算公司，版权所有。
 */
@RestController
@RequestMapping("/sdk-demo")
@Slf4j
public class SDKController {

    @Autowired
    private SDKService sdkService;

    @Autowired
    private FilePathConfigProperties filePathConfigProperties;

    @SneakyThrows
    @PostMapping("/sdkSendByJson")
    public Response sdkSendByJson(@RequestParam("file") MultipartFile file,
                                  @RequestParam(defaultValue = "001") String fileExtsInfo,
                                  @RequestParam(defaultValue = "0000000091") String senderCustId,
                                  @RequestParam(defaultValue = "0000000110") String senderSubCustId,
                                  @RequestParam(defaultValue = "0000000030") String deliveryToCustId,
                                  @RequestParam(defaultValue = "0000000051") String deliveryToSubCustId) {

        String filename = file.getOriginalFilename();
        final String filePath = filePathConfigProperties.getCommon().getUpload() + filename;
        log.info("临时文件保存路径:[{}]", filePath);
        cn.hutool.core.io.FileUtil.del(filePath);
        cn.hutool.core.io.FileUtil.writeFromStream(file.getInputStream(), new File(filePath));
        log.info("临时文件保存成功");

        final SendFileRequest request = new SendFileRequest();
        request.setFileFullPath(filePath);
        request.setFileExtsInfo(fileExtsInfo);
        request.setSenderCustId(senderCustId);
        request.setSenderSubCustId(senderSubCustId);
        request.setDeliveryToCustId(deliveryToCustId);
        request.setDeliveryToSubCustId(deliveryToSubCustId);
        return sdkService.sdkSend(request);
    }

    @PostMapping("/onReceivedMsg")
    public Response onReceivedMsg(@RequestParam String serino) {
        return sdkService.queryReceivedMessage(serino);
    }
}
