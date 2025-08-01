package com.aibank.wm.zd.client.config;

import lombok.Data;
import org.apache.commons.net.ftp.FTP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "path")
@Data
public class FilePathConfigProperties {
    private Common common;
    private ZD zd;

    @Data
    public static class Common {
        private String file;
        private String upload;
    }

    @Data
    public static class ZD {
        private String originCert;
        private String cert;
        private String data;
        private String share;
        private String send;
        private String sendFile;
        private String sendBakFile;
        private String receiveFile;
        private Set<String> receipt;
    }

}
