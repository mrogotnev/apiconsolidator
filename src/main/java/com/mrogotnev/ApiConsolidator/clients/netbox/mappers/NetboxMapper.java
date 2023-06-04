package com.mrogotnev.ApiConsolidator.clients.netbox.mappers;

import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiCluster;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiVM;
import com.mrogotnev.ApiConsolidator.dto.PojoNetboxCluster;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Component
public class NetboxMapper {
    public PojoVM netboxApiVMToPojoVM(NetboxApiVM.NetboxVM netboxVM, HashMap<String, Long> netboxClustersArrayList) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(netboxVM.getName());
        pojoVM.setCluster(netboxVM.getClusterName());
        pojoVM.setClusterId(netboxClustersArrayList.get(pojoVM.getCluster()));
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
