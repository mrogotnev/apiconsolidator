package com.mrogotnev.ApiConsolidator.clients.dto;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxCluster;
import com.mrogotnev.ApiConsolidator.clients.mappers.NetboxMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class PojoVM {
    private String name;
    private String cluster;
    private long clusterId;
    private double vcpu;
    private long memory;
    private int disk;
    private String status;
}
