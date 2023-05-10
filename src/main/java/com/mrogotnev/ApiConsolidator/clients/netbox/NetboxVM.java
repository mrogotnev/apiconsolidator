package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;

@Data
public class NetboxVM {
    private String name;
    private String cluster;
    private double vcpu;
    private long memory;
    private int disk;
    private String status;
}
