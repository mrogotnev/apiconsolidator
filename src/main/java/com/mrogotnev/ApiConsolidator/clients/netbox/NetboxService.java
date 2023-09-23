package com.mrogotnev.ApiConsolidator.clients.netbox;


import com.mrogotnev.ApiConsolidator.clients.netbox.mappers.NetboxMapper;
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
    private HashMap<String, Long> netboxPojoClustersMap;

    public NetboxApiVM getNetboxAPIVm() {
        netboxApiVM = webClientWithoutSSL
                .get()
                .uri(netboxCredentials.getIp() + ":" +
                        netboxCredentials.getPort() +
                        "/api/virtualization/virtual-machines/?limit=0")
                .header("Authorization", "TOKEN " + netboxCredentials.getToken())
                .retrieve()
                .bodyToMono(NetboxApiVM.class)
                .block();
        return netboxApiVM;
    }

    public HashSet<PojoVM> getPojoNetboxVM() {
        if (netboxCredentials.getIp() != null) {
            getNetboxAPIVm();
            HashSet<PojoVM> netboxPojoVM = new HashSet<>();
            for (NetboxApiVM.NetboxVM currentApiVM : netboxApiVM.getResults()) {
                PojoVM pojoVM = netboxMapper.netboxApiVMToPojoVM(currentApiVM, netboxCredentials);
                netboxPojoVM.add(pojoVM);
            }
            return netboxPojoVM;
        }
        return new HashSet<>();
    }
}
