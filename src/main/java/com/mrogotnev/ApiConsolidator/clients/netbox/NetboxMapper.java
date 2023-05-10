package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class NetboxMapper {
    private ArrayList<NetboxVM> netboxVMArrayList = new ArrayList<>();

    public ArrayList<NetboxVM> netboxApiVMToNetboxVM (NetboxApiVM netboxApiVM) {
        for (NetboxApiVM.NetboxVM currentFromAPI : netboxApiVM.getResults()) {
            NetboxVM netboxVM = new NetboxVM();
            netboxVM.setName(currentFromAPI.getName());
            netboxVM.setCluster(currentFromAPI.getClusterName());
            netboxVM.setVcpu(currentFromAPI.getVcpus());
            netboxVM.setMemory(currentFromAPI.getMemory());
            netboxVM.setDisk(currentFromAPI.getDisk());
            netboxVM.setStatus(currentFromAPI.getStatusValue());
            netboxVMArrayList.add(netboxVM);
        }
        return netboxVMArrayList;
    }

}
