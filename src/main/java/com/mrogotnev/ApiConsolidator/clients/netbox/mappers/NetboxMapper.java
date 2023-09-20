package com.mrogotnev.ApiConsolidator.clients.netbox.mappers;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxCredentials;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiCluster;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiVM;
import com.mrogotnev.ApiConsolidator.dto.PojoNetboxCluster;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Component
@AllArgsConstructor
public class NetboxMapper {
    private NetboxCredentials netboxCredentials;
    public PojoVM netboxApiVMToPojoVM(NetboxApiVM.NetboxVM netboxVM) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(netboxVM.getName().toLowerCase());

        HashMap<String, String> clusterNamesMap = netboxCredentials.getClusterNamesMap();
        if (clusterNamesMap.containsKey(netboxVM.getClusterName())) {
            pojoVM.setCluster(clusterNamesMap.get(netboxVM.getClusterName()));
        } else {
            pojoVM.setCluster(netboxVM.getClusterName());
        }

        pojoVM.setVcpu(netboxVM.getVcpus());
        pojoVM.setMemory(netboxVM.getMemory());
        pojoVM.setDisk(netboxVM.getDisk());
        pojoVM.setStatus(netboxVM.getStatusValue());
        return pojoVM;
    }

    public PojoNetboxCluster netboxApiClusterToNetboxCluster(NetboxApiCluster.ApiCluster apiCluster) {
        PojoNetboxCluster pojoNetboxCluster = new PojoNetboxCluster();
        pojoNetboxCluster.setId(apiCluster.getId());
        pojoNetboxCluster.setName(apiCluster.getName());
        return pojoNetboxCluster;
    }
}
