package com.aibank.wm.zd.client.schedule;

import cn.hutool.core.io.FileUtil;
import com.aibank.wm.zd.client.config.FilePathConfigProperties;
import com.aibank.wm.zd.client.dao.MessageStore;
import com.aibank.wm.zd.client.enums.LoggerCode;
import com.aibank.wm.zd.client.pojo.ReceiveMessage;
import com.aibank.wm.zd.client.service.ZDFileTransferService;
import com.aibank.wm.zd.client.util.DateUtils;
import com.aibank.wm.zd.client.util.FileNameUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.aibank.wm.zd.client.constants.CommonConstant.OK_FILE_SUFFIX;

@Component
@Slf4j
@Profile("zd")
public class ZDFileTransferSchedule {


    @Autowired
    private FilePathConfigProperties filePathConfigProperties;

    @Autowired
    private ZDFileTransferService fileTransferService;

    private static final int MAX_PROCESS_TIMES = 30;

    /**
     * 轮询收到中登文件缓存列表
     */
    @SneakyThrows
    @Scheduled(fixedDelayString = "${scheduled.zd.receiveMessageScan}")
    public void receiveMessageScan() {
        log.info("execute task ZD receiveMessageScan");
        final BlockingQueue<ReceiveMessage> receiveMessageQueue = MessageStore.receiveMessageQueue;
        final int size = receiveMessageQueue.size() + 1;
        for (int i = 0; i < size; i++) {
            ReceiveMessage message = receiveMessageQueue.poll(5, TimeUnit.SECONDS);
            try {
                if (message != null) {
                    log.info("消费消息 {} ", message);
                    fileTransferService.receiveFile(message);
                }
            } catch (Exception e) {
                if (message != null && message.getProcessTimes() < MAX_PROCESS_TIMES) {
                    log.error("{}  {} 查询收到文件流水失败,放入队列下次重试", LoggerCode.UNKNOWN_ERROR, message, e);
                    message.setProcessTimes(message.getProcessTimes() + 1);
                    receiveMessageQueue.put(message);
                } else {
                    log.error(" {} 超过最大重试次数,不再重试", message, e);
                }
            }
        }
        log.info("execute task ZD receiveMessageScan end");
    }

    /**
     * 轮询发送到中登文件
     */
    @Scheduled(fixedDelayString = "${scheduled.zd.sendFileScan}")
    public void sendFileScan() {
        log.info("execute task ZD sendFileScan");
        final ImmutableMap<String, Object> extractVariables = ImmutableMap.of("date", DateUtils.getYearMonthDay(DateUtils.getCurrentDateTime()));
        final String sendFilePath = FileNameUtil.stringSubstitute(filePathConfigProperties.getZd().getSend(), extractVariables);
        final List<File> sendFiles = FileUtil.loopFiles(sendFilePath);

        final Set<String> okFileCollect = sendFiles.stream()
                .filter(f -> f.getName().endsWith(OK_FILE_SUFFIX))
                .filter(f -> FileNameUtil.okFileMatch(f.getName()))
                .map(f -> f.getPath().substring(0, f.getPath().length() - OK_FILE_SUFFIX.length()))
                .filter(f -> !f.endsWith(OK_FILE_SUFFIX))
                .collect(Collectors.toSet());

        final List<File> collect = sendFiles.stream().filter(f -> okFileCollect.contains(f.getPath())).collect(Collectors.toList());


        log.info("目录 {} ,待发送文件\n {} \n", sendFilePath, Joiner.on("\n").join(collect));
        for (File sendFile : collect) {
            try {
                fileTransferService.sendFile(sendFile.getPath());
            } catch (Exception e) {
                log.error("发送文件失败", e);
            }
        }
        log.info("execute task ZD sendFileScan end");
    }
}
