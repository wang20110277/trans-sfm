package com.aibank.wm.zd.client.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.aibank.wm.zd.client.util.FileUtils;
import com.cdc.gateway.sdk.main.GatewaySdk;
import com.cdc.gateway.sdk.main.ReceiveCallBack;
import com.cdc.gateway.sdk.main.SdkConfiguration;
import com.aibank.wm.zd.client.service.impl.ReceiveCallBackImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.InputStream;

/**
 * @author libowen
 * @Package com.cdc.gateway.sdkDemo.config
 * @description
 * @date 2021/10/18 16:40
 * @Version v1.0
 * @copyright ©2017-2021 中央结算公司，版权所有。
 */

@Configuration
public class SdkConfig {

    @Autowired
    private FilePathConfigProperties filePathConfigProperties;

    @Bean
    @ConfigurationProperties(prefix = "sdkconfig")
    public SdkConfiguration sdkConfiguration() {
        return new SdkConfiguration();
    }

    @Bean
    public ReceiveCallBack receiveCallBack() {
        return new ReceiveCallBackImpl();
    }

    @Bean
    public GatewaySdk GatewaySdk(SdkConfiguration conf, ReceiveCallBack receiveCallBack) {
        GatewaySdk sdk = new GatewaySdk();

        final String originCert = filePathConfigProperties.getZd().getOriginCert();
        final String cert = filePathConfigProperties.getZd().getCert();

        final SdkConfiguration sdkConfiguration = new SdkConfiguration();
        BeanUtil.copyProperties(conf,sdkConfiguration);
        sdkConfiguration.setMsgDecryptPriKeyPath(copyFile(conf.getMsgDecryptPriKeyPath(),originCert,cert));
        sdkConfiguration.setMsgEncryptPubCertPath(copyFile(conf.getMsgEncryptPubCertPath(),originCert,cert));
        sdkConfiguration.setMsgSignCertPath(copyFile(conf.getMsgSignCertPath(),originCert,cert));
        sdkConfiguration.setSendHttpsTrustStorePath(copyFile(conf.getSendHttpsTrustStorePath(),originCert,cert));

        sdk.init(sdkConfiguration, receiveCallBack);
        return sdk;
    }

    private String copyFile(String fileName, String inPath, String destPath) {
        final String in = inPath + "/" + fileName;
        final String dest = destPath + "/" + fileName;
        final InputStream resourceFileStream = FileUtils.getResourceFileStream(in);
        FileUtil.writeFromStream(resourceFileStream, new File(dest));
        return dest;
    }
}
