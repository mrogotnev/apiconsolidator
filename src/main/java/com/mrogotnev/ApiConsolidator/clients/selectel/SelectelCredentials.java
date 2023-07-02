package com.mrogotnev.ApiConsolidator.clients.selectel;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "selectel")
@Data
public class SelectelCredentials {
    private String username;
    private String password;
    private String tenantName;
    private String loginUrl;
    private String apiVersion;

}
