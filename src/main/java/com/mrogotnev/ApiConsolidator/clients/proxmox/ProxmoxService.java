package com.mrogotnev.ApiConsolidator.clients.proxmox;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;


@AllArgsConstructor
@Data
@Component
public class ProxmoxService {
    private final WebClient webClientWithoutSSL;
    private ProxmoxTicketAndToken proxmoxTicketAndToken;
    private ProxmoxCredentials proxmoxCredentials;
    private ProxmoxNodeName proxmoxNodeName;
    private ArrayList<ProxmoxDatacenter> proxmoxDatacenterList = new ArrayList<>();
    private ProxmoxApiVM proxmoxApiVM;
    private ProxmoxValidator proxmoxValidator;
    private ProxmoxMapper proxmoxMapper;

    public String getVMList() {
        String res = "";
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers()) {
             res = webClientWithoutSSL
                    .get()
                    .uri("https://" + currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/")
                    .header("Authorization", "PVEAPIToken=" +
                            currentServer.getUserLogin() + "!" +
                            currentServer.getTokenID() + "=" +
                            currentServer.getTokenKey())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
        return res;
    }

    /*public ArrayList<ProxmoxDatacenter> getAllProxmoxTickets() {
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers()) {

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("username", currentServer.getLogin());
            formData.add("password", currentServer.getPassword());
            proxmoxTicketAndToken = webClientWithoutSSL
                    .post()
                    .uri("https://" + currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/access/ticket")
                    .body(BodyInserters.fromFormData(formData))
                    .exchange()
                    .block()
                    .bodyToMono(ProxmoxTicketAndToken.class)
                    .block();
            proxmoxDatacenterList.add(new ProxmoxDatacenter(currentServer.getIp(), currentServer.getPort(), proxmoxTicketAndToken));

        }
        return proxmoxDatacenterList;
    }*/

    /*public ArrayList<ProxmoxDatacenter> getAllNodeNames() {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            proxmoxNodeName = webClientWithoutSSL.get()
                    .uri("https://" + currentDatacenter.getIp() + ":" + currentDatacenter.getPort() + "/api2/json/cluster/status")
                    .cookie("PVEAuthCookie", currentDatacenter.getProxmoxTicketAndToken().getTicket())
                    .header("CSRFPreventionToken", currentDatacenter.getProxmoxTicketAndToken().getToken())
                    .retrieve()
                    .bodyToMono(ProxmoxNodeName.class)
                    .block();
            for (ProxmoxNodeName.Data currentData : proxmoxNodeName.getData()) {
                currentDatacenter.getProxmoxNodeArrayList().add(new ProxmoxNode(currentData.getName()));
            }
        }
        return proxmoxDatacenterList;
    }*/

    /*public ArrayList<ProxmoxDatacenter> getAllProxmoxVM() {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            for (ProxmoxNode currentNode : currentDatacenter.getProxmoxNodeArrayList()) {
                proxmoxApiVM = webClientWithoutSSL.get()
                        .uri("https://" + currentDatacenter.getIp() + ":" + currentDatacenter.getPort()
                                + "/api2/json/nodes/" + currentNode.getName() + "/qemu")
                        .cookie("PVEAuthCookie", currentDatacenter.getProxmoxTicketAndToken().getTicket())
                        .header("CSRFPreventionToken", currentDatacenter.getProxmoxTicketAndToken().getToken())
                        .retrieve()
                        .bodyToMono(ProxmoxApiVM.class)
                        .block();
                currentNode.setVmArrayList(proxmoxApiVM.getData());
            }
        }
        return proxmoxDatacenterList;
    }*/

    /*public ArrayList<ProxmoxVM> getMapperedProxVMS(){
        //TODO: Need to refactor!
        getAllProxmoxTickets();
        getAllNodeNames();
        //end
        return proxmoxMapper.proxmoxApiVMToProxmoxVM(getAllProxmoxVM());
    }*/
}
