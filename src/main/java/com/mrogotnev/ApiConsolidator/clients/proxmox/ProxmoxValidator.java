package com.mrogotnev.ApiConsolidator.clients.proxmox;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProxmoxValidator {

    public boolean proxmoxDatacenterAlreadyExist(ProxmoxCredentials.ProxmoxServer proxmoxServer, ArrayList<ProxmoxDatacenter> proxmoxDatacenterArrayList) {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterArrayList) {
            return currentDatacenter.getIp().equals(proxmoxServer.getIp());
        }
        return false;
    }
}
