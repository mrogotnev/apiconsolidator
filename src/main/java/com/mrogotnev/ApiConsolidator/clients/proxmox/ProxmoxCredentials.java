package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ConfigurationProperties(prefix = "proxmox")
@Data
public class ProxmoxCredentials {

    private ArrayList<ProxmoxServer> proxmoxServers;

    @Data
    static class ProxmoxServer {
    private String ip;
    private String port;
    private String userLogin;
    private String tokenID;
    private String tokenKey;
    }
}
