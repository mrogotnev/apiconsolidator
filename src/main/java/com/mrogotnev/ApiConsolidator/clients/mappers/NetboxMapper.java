package com.mrogotnev.ApiConsolidator.clients.mappers;

import com.mrogotnev.ApiConsolidator.clients.dto.PojoVM;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiCluster;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxApiVM;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxCluster;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxVM;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Component
public class NetboxMapper {
    private ArrayList<PojoVM> netboxVMArrayList = new ArrayList<>();
    private HashMap<String, Long> netboxClustersArrayList = new HashMap<>();

    public PojoVM netboxApiVMToPojoVM(NetboxApiVM.NetboxVM netboxVM) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(netboxVM.getName());
        pojoVM.setCluster(netboxVM.getClusterName());
        pojoVM.setClusterId(netboxClustersArrayList.get(pojoVM.getName()));
        pojoVM.setVcpu(netboxVM.getVcpus());
        pojoVM.setMemory(netboxVM.getMemory());
        pojoVM.setDisk(netboxVM.getDisk());
        pojoVM.setStatus(netboxVM.getStatusValue());
        netboxVMArrayList.add(pojoVM);
        return pojoVM;
    }

    public HashMap<String, Long> netboxApiClusterToNetboxCluster(NetboxApiCluster netboxApiCluster) {
        for (NetboxApiCluster.ApiCluster currentFromApi : netboxApiCluster.getResults()) {
            NetboxCluster netboxCluster = new NetboxCluster();
            netboxCluster.setId(currentFromApi.getId());
            netboxCluster.setName(currentFromApi.getName());
            netboxClustersArrayList.put(netboxCluster.getName(), netboxCluster.getId());
        }
        return netboxClustersArrayList;
    }
}
