package com.mrogotnev.ApiConsolidator.clients.proxmox.mappers;

import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxApiVM;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProxmoxMapper {
    public PojoVM proxmoxApiVMToPojoVM(ProxmoxApiVM.ProxmoxVM proxmoxApiVM, String clusterName) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setIdFromOutSystems(proxmoxApiVM.getVmid());
        pojoVM.setName(proxmoxApiVM.getName());
        pojoVM.setCluster(clusterName);
        pojoVM.setVcpu(proxmoxApiVM.getCpus());
        pojoVM.setMemory((long) (proxmoxApiVM.getMaxmem()/Math.pow(1024, 2)));
        pojoVM.setDisk((int) (proxmoxApiVM.getMaxdisk()/Math.pow(1024, 3)));
        pojoVM.setStatus(proxmoxApiVM.getStatus());
        return pojoVM;
    }

}
