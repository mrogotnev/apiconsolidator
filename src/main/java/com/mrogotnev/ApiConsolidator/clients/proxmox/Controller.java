package com.mrogotnev.ApiConsolidator.clients.proxmox;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@AllArgsConstructor
public class Controller {
    private ProxmoxService proxmoxService;

    @GetMapping("/getAllProxmoxTickets")
    public ArrayList<ProxmoxDatacenter> getAllProxmoxTickets() {
        return proxmoxService.getAllProxmoxTickets();
    }

    @GetMapping("/getAllNodeNames")
    public ArrayList<ProxmoxDatacenter> getAllNodeNames() {
        return proxmoxService.getAllNodeNames();
    }

    @GetMapping("/getAllProxmoxVM")
    public ArrayList<ProxmoxDatacenter> getAllProxmoxVM() {
        return proxmoxService.getAllProxmoxVM();
    }

    @GetMapping("/getAllData")
    public ArrayList<ProxmoxDatacenter> getAllData() {
        getAllProxmoxTickets();
        getAllNodeNames();
        return getAllProxmoxVM();
    }
}
