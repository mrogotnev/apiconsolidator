package com.mrogotnev.ApiConsolidator.clients.netbox;


import com.mrogotnev.ApiConsolidator.clients.netbox.mappers.NetboxMapper;
import com.mrogotnev.ApiConsolidator.dto.PojoNetboxCluster;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.HashSet;

@AllArgsConstructor
@Data
@Component
public class NetboxService {
    private NetboxCredentials netboxCredentials;
    private final WebClient webClientWithoutSSL;
    private NetboxApiVM netboxApiVM;
    private NetboxMapper netboxMapper;
    private NetboxApiCluster netboxApiCluster;
    private HashMap<String, Long> netboxPojoClustersMap = new HashMap<>();
    private HashSet<PojoVM> netboxPojoVM = new HashSet<>();

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

    public HashMap<String, Long> getPojoNetboxClusters() {
        getNetboxAPIClusters();
        for (NetboxApiCluster.ApiCluster currentApiCluster : netboxApiCluster.getResults()) {
            PojoNetboxCluster cluster = netboxMapper.netboxApiClusterToNetboxCluster(currentApiCluster);
            netboxPojoClustersMap.put(cluster.getName(), cluster.getId());
        }
        return netboxPojoClustersMap;
    }

    public HashSet<PojoVM> getPojoNetboxVM() {
        getNetboxAPIVm();
        for (NetboxApiVM.NetboxVM currentApiVM : netboxApiVM.getResults()) {
            PojoVM pojoVM = netboxMapper.netboxApiVMToPojoVM(currentApiVM);
            netboxPojoVM.add(pojoVM);
        }
        return netboxPojoVM;
    }
}
