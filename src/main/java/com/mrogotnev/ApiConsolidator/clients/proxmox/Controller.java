package com.mrogotnev.ApiConsolidator.clients.proxmox;


import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


@RestController
@AllArgsConstructor
public class Controller {
    private ProxmoxService proxmoxService;

    @GetMapping("/getVMList")
    public HashSet<PojoVM> getVMList() {
        return proxmoxService.getVMList();
    }
}
