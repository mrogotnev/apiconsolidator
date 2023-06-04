package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Component
public class NetboxClusterMatcher {
    private ArrayList<NetboxCluster> netboxClusters = new ArrayList<>();

}
