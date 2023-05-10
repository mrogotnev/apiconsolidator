package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiVM;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxService;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxVM;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxService;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@RestController
public class ProxmoxNetboxComparisonService {
    private ProxmoxService proxmoxService;
    private NetboxService netboxService;
    private ProxmoxNetboxComparisonResult proxmoxNetboxComparisonResult;

    /*@GetMapping("/NetboxAndProxmoxDifference")
    public ArrayList<ProxmoxVM> getFirstDiff() {
        proxmoxService.getMapperedProxVMS();
        netboxService.getNetboxVMS();
        ArrayList<ProxmoxVM> proxmoxVMS = new ArrayList<>(proxmoxService.getMapperedProxVMS());
        ArrayList<NetboxVM> netboxVMS = new ArrayList<>(netboxService.getNetboxVMS());

        for (ProxmoxVM currentProxmoxVM : proxmoxVMS) {
            for (NetboxVM currentNetboxVM : netboxVMS) {
                if(currentProxmoxVM.getName().equals(currentNetboxVM.getName())) {
                    proxmoxVMS.remove(currentProxmoxVM);
                }
            }
        }
        proxmoxNetboxComparisonResult.setProxmoxVMSWhichIsNotInNetbox(proxmoxVMS);
        return proxmoxNetboxComparisonResult.getProxmoxVMSWhichIsNotInNetbox();
    }*/
}
