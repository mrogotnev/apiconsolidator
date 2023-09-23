package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.mrogotnev.ApiConsolidator.clients.proxmox.mappers.ProxmoxMapper;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashSet;


@AllArgsConstructor
@Data
@Component
public class ProxmoxService {
    private final WebClient webClientWithoutSSL;
    private ProxmoxCredentials proxmoxCredentials;
    private ProxmoxNodeName proxmoxNodeName;
    private ArrayList<ProxmoxDatacenter> proxmoxDatacenterList;
    private ProxmoxMapper proxmoxMapper;
    private HashSet<PojoVM> proxmoxPojoVM;

    public void getAllNodeNames() {
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers()) {
                proxmoxNodeName = webClientWithoutSSL
                        .get()
                        .uri(currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/cluster/status")
                        .header("Authorization", "PVEAPIToken=" +
                                currentServer.getUserLogin() + "!" +
                                currentServer.getTokenID() + "=" +
                                currentServer.getTokenKey())
                        .retrieve()
                        .bodyToMono(ProxmoxNodeName.class)
                        .block();
                proxmoxDatacenterList.add(new ProxmoxDatacenter(currentServer, proxmoxNodeName));
        }
    }

    public void proxmoxApiVMArraylists() {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            for (ProxmoxNodeName.Data currentData : currentDatacenter.getProxmoxNodeName().getData()) {
                ProxmoxNode proxmoxNode = new ProxmoxNode(currentData.getName());
                ProxmoxApiVM proxmoxApiVM;
                proxmoxApiVM = webClientWithoutSSL
                        .get()
                        .uri(currentDatacenter.getProxmoxServer().getIp() +
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
    }

    public HashSet<PojoVM> getVMList() {
        if (proxmoxCredentials.getProxmoxServers() != null) {
            getAllNodeNames();
            proxmoxApiVMArraylists();
            for (ProxmoxDatacenter currentDatacenter : getProxmoxDatacenterList()) {
                for (ProxmoxNode currentNode : currentDatacenter.getProxmoxNodes()) {
                    for (ProxmoxApiVM currentApiVM : currentNode.getVmArrayList())
                        for (ProxmoxApiVM.ProxmoxVM currentVM : currentApiVM.getData()) {
                            PojoVM currentPojoVM = proxmoxMapper.proxmoxApiVMToPojoVM(currentVM, currentNode.getName());
                            proxmoxPojoVM.add(currentPojoVM);
                        }
                }
            }
        }
        return proxmoxPojoVM;
    }

}

