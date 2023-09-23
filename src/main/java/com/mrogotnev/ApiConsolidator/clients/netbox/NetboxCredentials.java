package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "netbox")
@Data
public class NetboxCredentials {
    private String ip;
    private String port;
    private String token;
    @Value("#{${netbox.clusterNamesMap.map:null}}")
    private HashMap<String, String> clusterNamesMap;
}
