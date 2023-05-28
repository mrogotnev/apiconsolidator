package com.mrogotnev.ApiConsolidator.clients.proxmox;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;


@RestController
@AllArgsConstructor
public class Controller {
    private ProxmoxService proxmoxService;

    @GetMapping("/getVMList")
    public HashMap<String, LinkedList<ProxmoxVM>> getVMList() {
        return proxmoxService.getVMList();
    }
}
