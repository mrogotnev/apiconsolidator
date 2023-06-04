package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.mrogotnev.ApiConsolidator.clients.proxmox.mappers.ProxmoxMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


@AllArgsConstructor
@Data
@Component
public class ProxmoxService {
    private final WebClient webClientWithoutSSL;
    private ProxmoxCredentials proxmoxCredentials;
    private ProxmoxNodeName proxmoxNodeName;
    private ArrayList<ProxmoxDatacenter> proxmoxDatacenterList = new ArrayList<>();
    private ProxmoxApiVM proxmoxApiVM;
    private ProxmoxMapper proxmoxMapper;

    public ArrayList<ProxmoxDatacenter> getAllNodeNames() {
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers()) {
            proxmoxNodeName = webClientWithoutSSL
                    .get()
                    .uri("https://" + currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/cluster/status")
                    .header("Authorization", "PVEAPIToken=" +
                            currentServer.getUserLogin() + "!" +
                            currentServer.getTokenID() + "=" +
                            currentServer.getTokenKey())
                    .retrieve()
                    .bodyToMono(ProxmoxNodeName.class)
                    .block();
            proxmoxDatacenterList.add(new ProxmoxDatacenter(currentServer, proxmoxNodeName));

        }
        return proxmoxDatacenterList;
    }

    public ArrayList<ProxmoxDatacenter> proxmoxApiVMArraylists() {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            for (ProxmoxNodeName.Data currentData : currentDatacenter.getProxmoxNodeName().getData()) {
                ProxmoxNode proxmoxNode = new ProxmoxNode(currentData.getName());
                ProxmoxApiVM proxmoxApiVM = new ProxmoxApiVM();
                proxmoxApiVM = webClientWithoutSSL
                        .get()
                        .uri("https://" + currentDatacenter.getProxmoxServer().getIp() +
                                ":" + currentDatacenter.getProxmoxServer().getPort() +
                                "/api2/json/nodes/" +
                                currentData.getName() +
                                "/qemu")
                        .header("Authorization", "PVEAPIToken=" +
                                currentDatacenter.getProxmoxServer().getUserLogin() + "!" +
                                currentDatacenter.getProxmoxServer().getTokenID() + "=" +
                                currentDatacenter.getProxmoxServer().getTokenKey())
                        .retrieve()
                        .bodyToMono(ProxmoxApiVM.class)
                        .block();
                proxmoxNode.getVmArrayList().add(proxmoxApiVM);
                currentDatacenter.getProxmoxNodes().add(proxmoxNode);
            }
        }
        return proxmoxDatacenterList;
    }

    public HashMap<String, LinkedList<ProxmoxVM>> getVMList() {
        getAllNodeNames();
        proxmoxApiVMArraylists();
        for (ProxmoxDatacenter currentDatacenter : getProxmoxDatacenterList()) {
            proxmoxMapper.ProxmoxVMApiListToSimpleProxmoxVmHashMap(currentDatacenter);
        }
        return proxmoxMapper.getProxmoxVMHashMap();
    }
}
