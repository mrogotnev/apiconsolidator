package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;

@Data
public class ProxmoxVM {
    private String name;
    private String cluster;
    private double vcpu;
    private long memory;
    private int disk;
    private String status;
}
