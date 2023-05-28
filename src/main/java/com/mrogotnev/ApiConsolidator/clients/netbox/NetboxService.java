package com.mrogotnev.ApiConsolidator.clients.netbox;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ArrayList<NetboxVM> getNetboxVMS() throws JsonProcessingException {
        addNetboxAPIVm();
        return netboxMapper.netboxApiVMToNetboxVM(getNetboxAPIVm());
    }

    public boolean addNetboxAPIVm() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NetboxApiVM.NetboxVM netboxVM = new NetboxApiVM.NetboxVM();
        netboxVM.setName("Test1");
        netboxVM.setCluster(new NetboxApiVM.NetboxVM.Cluster("TestCluster"));
        netboxVM.setVcpus(1.0);
        netboxVM.setMemory(123456);
        netboxVM.setDisk(50);
        netboxVM.setStatus(new NetboxApiVM.NetboxVM.Status("active"));
        String result = objectMapper.writeValueAsString(netboxVM);
        return true;
    }
}
