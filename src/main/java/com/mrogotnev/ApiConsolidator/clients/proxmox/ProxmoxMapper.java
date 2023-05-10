package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class ProxmoxMapper {
    private ArrayList<ProxmoxVM> proxmoxVMArrayList = new ArrayList<>();
    public ArrayList<ProxmoxVM> proxmoxApiVMToProxmoxVM (ArrayList<ProxmoxDatacenter> proxmoxDatacenterList) {
        for(ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            for (ProxmoxNode currentNode : currentDatacenter.getProxmoxNodeArrayList()) {
                for (ProxmoxApiVM.ProxmoxVM currentVM : currentNode.getVmArrayList()) {
                    ProxmoxVM proxmoxVM = new ProxmoxVM();
                    proxmoxVM.setName(currentVM.getName());
                    proxmoxVM.setCluster(currentNode.getName());
                    proxmoxVM.setVcpu(currentVM.getCpus());
                    proxmoxVM.setMemory(currentVM.getMaxmem());
                    proxmoxVM.setDisk((int) (currentVM.getMaxdisk()/Math.pow(1024, 3)));
                    proxmoxVM.setStatus(currentVM.getStatus());
                    proxmoxVMArrayList.add(proxmoxVM);
                }
            }
        }
        return proxmoxVMArrayList;
    }
}
