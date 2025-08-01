package com.aibank.wm.zd.client.config;

import com.google.common.base.Strings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "organization")
@Data
@RefreshScope
public class OrganizationConfigProperties {
    private Organization me;
    private List<Organization> others;

    @Data
    public static class Organization {
        private String organizationName;
        private String organizationId;
        private String subOrganizationId;
        private String organizationCode;
        private String taCode;
        private String productSystem;

        private boolean allowNoCheck;
     }

    public Organization getByOrganizationCode(String organizationCode) {
        if (Strings.isNullOrEmpty(organizationCode)) {
            return null;
        }
        for (Organization other : others) {
            if (organizationCode.equals(other.getOrganizationCode())) {
                return other;
            }
        }
        return null;
    }
}
