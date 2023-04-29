package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "netbox")
@Data
public class NetboxCredentials {
    private String ip;
    private String port;
    private String token;
}
