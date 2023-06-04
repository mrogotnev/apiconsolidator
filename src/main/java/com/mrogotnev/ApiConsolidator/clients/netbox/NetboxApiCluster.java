package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class NetboxApiCluster {
    private ArrayList<ApiCluster> results;

    @Data
    public static class ApiCluster {
        private long id;
        private String name;
    }
}
