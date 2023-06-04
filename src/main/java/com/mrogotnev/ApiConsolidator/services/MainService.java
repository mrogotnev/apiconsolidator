package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.clients.netbox.mappers.NetboxMapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
public class MainService {
    private NetboxMapper netboxMapper;
    private HashMap<String, Long> netboxClusters = new HashMap<>();

    public void getPojoNetboxClusters() {
        //netboxClusters = netboxMapper.netboxApiClusterToNetboxCluster()
    }
}
