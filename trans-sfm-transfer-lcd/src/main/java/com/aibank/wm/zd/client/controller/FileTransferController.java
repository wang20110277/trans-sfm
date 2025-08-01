package com.aibank.wm.zd.client.controller;

import com.aibank.wm.zd.client.config.FilePathConfigProperties;
import com.aibank.wm.zd.client.config.OrganizationConfigProperties;
import com.aibank.wm.zd.client.dao.MessageStore;
import com.aibank.wm.zd.client.pojo.FileNameVariables;
import com.aibank.wm.zd.client.pojo.ReceiveMessage;
import com.aibank.wm.zd.client.service.ZDFileTransferService;
import com.aibank.wm.zd.client.util.DateUtils;
import com.aibank.wm.zd.client.util.FileNameUtil;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequestMapping("/fileTransfer")
@Slf4j
public class FileTransferController {

    @Autowired
    private ZDFileTransferService zdFileTransferService;

    @Autowired
    private FilePathConfigProperties filePathConfigProperties;

    @Autowired
    private OrganizationConfigProperties organizationConfigProperties;


    @SneakyThrows
    @PostMapping("/receiveFile")
    @ApiOperation("重复从中登拉取文件")
    public Object receiveFile(@RequestParam("serialNo") String serialNo,@RequestParam("api")  String api) {
        MessageStore.receiveMessageQueue.put(new ReceiveMessage(serialNo, api));
        //zdFileTransferService.receiveFile(new ReceiveMessage(serialNo,api));
        return "success";
    }



    @SneakyThrows
    @PostMapping("/sendToZD")
    @ApiOperation("文件发送到中登")
    public Object sendToZD(@RequestParam("filePath") String filePath) {
        zdFileTransferService.sendFile(filePath);
        return "success";
    }


    @SneakyThrows
    @PostMapping("/sendLocalFileToZD")
    @ApiOperation("上传并发送文件到ftp")
    public Object sendLocalFileToZD(@RequestParam("file") MultipartFile file) {
        FileNameVariables fileNameVariables = FileNameUtil.extractVariables(file.getOriginalFilename());
        if (fileNameVariables == null) {
            throw new IllegalArgumentException("文件名称不合法 " + file);
        }
        final OrganizationConfigProperties.Organization receiveOrganization = organizationConfigProperties.getByOrganizationCode(fileNameVariables.getReceiveOrganization());
        if (receiveOrganization == null) {
            throw new IllegalArgumentException("文件名称不合法 ,接收机构未配置: " + file.getOriginalFilename());
        }
        fileNameVariables.setDate(DateUtils.getYearMonthDay(DateUtils.getCurrentDateTime()));
        final String sendFilePath = FileNameUtil.stringSubstitute(filePathConfigProperties.getZd().getSendFile(),fileNameVariables);

        log.info("文件保存路径:[{}]", sendFilePath);
        cn.hutool.core.io.FileUtil.del(sendFilePath);
        cn.hutool.core.io.FileUtil.writeFromStream(file.getInputStream(), new File(sendFilePath));
        log.info("文件保存成功:[{}]", sendFilePath);
        zdFileTransferService.sendFile(sendFilePath);
        return "success";
    }

}
