package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
public class ProxmoxDatacenter {
    private String ip;
    private String port;
    private ProxmoxTicketAndToken proxmoxTicketAndToken;
    private ArrayList<ProxmoxNode> proxmoxNodeArrayList = new ArrayList<>();

    public ProxmoxDatacenter(String ip, String port, ProxmoxTicketAndToken proxmoxTicketAndToken) {
        this.ip = ip;
        this.port = port;
        this.proxmoxTicketAndToken = proxmoxTicketAndToken;

    }
}
