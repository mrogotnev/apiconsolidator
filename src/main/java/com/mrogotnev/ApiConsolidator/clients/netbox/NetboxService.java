package com.mrogotnev.ApiConsolidator.clients.netbox;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.clients.dto.PojoVM;
import com.mrogotnev.ApiConsolidator.clients.mappers.NetboxMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
@Data
@Component
public class NetboxService {
    private NetboxCredentials netboxCredentials;
    private final WebClient webClientWithoutSSL;
    private NetboxApiVM netboxApiVM;
    private NetboxMapper netboxMapper;
    private NetboxApiCluster netboxApiCluster;

    private ArrayList<NetboxCluster> netboxClusterArrayList = new ArrayList<>();

    public NetboxApiCluster getNetboxAPIClusters() {
        netboxApiCluster = webClientWithoutSSL
                .get()
                .uri("https://" +
                        netboxCredentials.getIp() + ":" +
                        netboxCredentials.getPort() +
                        "/api/virtualization/clusters/?limit=0")
                .header("Authorization", "TOKEN " + netboxCredentials.getToken())
                .retrieve()
                .bodyToMono(NetboxApiCluster.class)
                .block();
        return netboxApiCluster;
    }

    public HashMap<String, Long> getNetboxClusters() {
        getNetboxAPIClusters();
        return netboxMapper.netboxApiClusterToNetboxCluster(netboxApiCluster);
    }

    public NetboxApiVM getNetboxAPIVm() {
        netboxApiVM = webClientWithoutSSL
                .get()
                .uri("https://" +
                        netboxCredentials.getIp() + ":" +
                        netboxCredentials.getPort() +
                        "/api/virtualization/virtual-machines/?limit=0")
                .header("Authorization", "TOKEN " + netboxCredentials.getToken())
                .retrieve()
                .bodyToMono(NetboxApiVM.class)
                .block();
        return netboxApiVM;
    }

    public ArrayList<PojoVM> getNetboxVMS() throws JsonProcessingException {
        getNetboxClusters();
        getNetboxAPIVm();
        return netboxMapper.netboxApiVMToPojoVM(getNetboxAPIVm());
    }

    public boolean addNetboxAPIVm() throws JsonProcessingException {
        return true;
    }
}
