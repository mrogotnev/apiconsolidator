package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProxmoxNode {
    private String name;
    private ArrayList<ProxmoxApiVM.ProxmoxVM> vmArrayList;

    public ProxmoxNode(String name) {
        this.name = name;
    }
}
