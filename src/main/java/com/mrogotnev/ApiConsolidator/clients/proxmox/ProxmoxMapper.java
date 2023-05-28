package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@Data
@Component
public class ProxmoxMapper {
    private HashMap<String, LinkedList<ProxmoxVM>> proxmoxVMHashMap = new HashMap<>();

    public HashMap<String, LinkedList<ProxmoxVM>> ProxmoxVMApiListToSimpleProxmoxVmHashMap(ProxmoxDatacenter proxmoxDatacenter) {
        for (ProxmoxNode currentNode : proxmoxDatacenter.getProxmoxNodes()) {
            for (ProxmoxApiVM currentApiVMData : currentNode.getVmArrayList()) {
                for (ProxmoxApiVM.ProxmoxVM currentVM : currentApiVMData.getData()) {
                    addVmToHashMap(proxmoxVMHashMap, proxmoxVMApiToSimpleProxmoxVM(currentVM, currentNode.getName()));
                }
            }
        }
        return proxmoxVMHashMap;
    }
    public ProxmoxVM proxmoxVMApiToSimpleProxmoxVM(ProxmoxApiVM.ProxmoxVM proxmoxVMFromApi, String clusterName) {
        ProxmoxVM proxmoxVM = new ProxmoxVM();
        proxmoxVM.setName(proxmoxVMFromApi.getName());
        proxmoxVM.setVcpu(proxmoxVMFromApi.getCpus());
        proxmoxVM.setMemory(proxmoxVMFromApi.getMaxmem());
        proxmoxVM.setDisk(proxmoxVMFromApi.getMaxmem());
        proxmoxVM.setStatus(proxmoxVMFromApi.getStatus());
        proxmoxVM.setCluster(clusterName);
        return proxmoxVM;
    }
    public void addVmToHashMap(HashMap<String, LinkedList<ProxmoxVM>> proxmoxVMHashMap, ProxmoxVM proxmoxVM) {
        if (!proxmoxVMHashMap.containsKey(proxmoxVM.getName())) {
            proxmoxVMHashMap.put(proxmoxVM.getName(), new LinkedList<ProxmoxVM>());
        }
        proxmoxVMHashMap.get(proxmoxVM.getName()).add(proxmoxVM);
    }

}
