package com.mrogotnev.ApiConsolidator.clients.teampass;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ConfigurationProperties(prefix = "teampass")
@Data
public class TeampassCredentials {
    private String url;
    private String apiToken;
    private ArrayList<String> foldersName;
}
