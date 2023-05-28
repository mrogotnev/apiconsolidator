package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;
import java.util.ArrayList;

@Data
public class ProxmoxDatacenter {
    private ProxmoxCredentials.ProxmoxServer proxmoxServer;
    private ProxmoxNodeName proxmoxNodeName;
    private ArrayList<ProxmoxNode> proxmoxNodes = new ArrayList<>();


    public ProxmoxDatacenter(ProxmoxCredentials.ProxmoxServer proxmoxServer, ProxmoxNodeName proxmoxNodeName) {
        this.proxmoxServer = proxmoxServer;
        this.proxmoxNodeName = proxmoxNodeName;
    }
}
