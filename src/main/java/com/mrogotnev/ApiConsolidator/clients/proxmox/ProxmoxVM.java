package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;

@Data
public class ProxmoxVM {
    private String name;
    private String cluster;
    private double vcpu;
    private long memory;
    private long disk;
    private String status;
}
