package com.mrogotnev.ApiConsolidator.clients.netbox;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Component
public class NetboxService {
    private NetboxCredentials netboxCredentials;
    private final WebClient webClientWithoutSSL;
    private NetboxApiVM netboxApiVM;
    private NetboxMapper netboxMapper;

    public NetboxApiVM netboxTestData() {
        netboxApiVM = webClientWithoutSSL.get()
                .uri("https://172.16.3.20:443/api/virtualization/virtual-machines/?limit=0")
                .header("Authorization", "TOKEN 0ae40be741e0fd8f7fff431bd4dbda44023714a6")
                .retrieve()
                .bodyToMono(NetboxApiVM.class)
                .block();
        return netboxApiVM;
    }

    public ArrayList<NetboxVM> getNetboxVMS() {
        return netboxMapper.netboxApiVMToNetboxVM(netboxTestData());
    }
}
