package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class ProxmoxApiVM {
    private ArrayList<ProxmoxVM> data;

    public ArrayList<ProxmoxVM> getData() {
        return data;
    }

    @lombok.Data
    @JsonIgnoreProperties(value = {"diskread", "diskwrite", "disk", "pid", "netin", "cpu", "netout", "uptime", "mem"})
    public static class ProxmoxVM {
        private String name;
        private String vmid;
        private long maxdisk;
        private long maxmem;
        private double cpus;
        private String status;
    }
}
