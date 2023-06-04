package com.mrogotnev.ApiConsolidator.clients.proxmox.mappers;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiVM;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxApiVM;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxDatacenter;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxNode;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxVM;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@Data
@Component
public class ProxmoxMapper {
    public PojoVM proxmoxApiVMToPojoVM(ProxmoxApiVM.ProxmoxVM proxmoxApiVM, HashMap<String, Long> netboxClustersArrayList) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(proxmoxApiVM.getName());
        pojoVM.setCluster(proxmoxApiVM.);
        pojoVM.setClusterId(netboxClustersArrayList.get(pojoVM.getCluster()));
        pojoVM.setVcpu(proxmoxApiVM.getCpus());
        pojoVM.setMemory(proxmoxApiVM.getMaxmem());
        pojoVM.setDisk(proxmoxApiVM.getMaxdisk());
        pojoVM.setStatus(netboxVM.getStatusValue());
        return pojoVM;
    }

}
