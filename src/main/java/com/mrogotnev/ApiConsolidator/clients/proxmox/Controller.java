package com.mrogotnev.ApiConsolidator.clients.proxmox;


import lombok.AllArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;
import java.util.ArrayList;


@RestController
@AllArgsConstructor
public class Controller {
    private final WebClient webClient;
    private ProxmoxTicketAndToken proxmoxTicketAndToken;
    private ProxmoxCredentials proxmoxCredentials;
    private ProxmoxNodeName proxmoxNodeName;
    private ArrayList<ProxmoxDatacenter> proxmoxDatacenterList;
    private ProxmoxApiVM proxmoxApiVM;

    @GetMapping("/getAllProxmoxTickets")
    public ArrayList<ProxmoxDatacenter> getAllProxmoxTickets() {
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers() ) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("username", currentServer.getLogin());
            formData.add("password", currentServer.getPassword());

            proxmoxTicketAndToken = webClient
                    .post()
                    .uri("https://"+ currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/access/ticket")
                    .body(BodyInserters.fromFormData(formData))
                    .exchange()
                    .block()
                    .bodyToMono(ProxmoxTicketAndToken.class)
                    .block();
            proxmoxDatacenterList.add(new ProxmoxDatacenter(currentServer.getIp(), currentServer.getPort(), proxmoxTicketAndToken));
        }
        return proxmoxDatacenterList;
    }

    @GetMapping("/getAllNodeNames")
    public ArrayList<ProxmoxDatacenter> getAllNodeNames() {
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            proxmoxNodeName = webClient.get()
                    .uri("https://"+ currentDatacenter.getIp() + ":" + currentDatacenter.getPort() + "/api2/json/cluster/status" )
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
    }

    @GetMapping("/getAllProxmoxVM")
    public ArrayList<ProxmoxDatacenter> getAllProxmoxVM() {
        String responseJson = "";
        for (ProxmoxDatacenter currentDatacenter : proxmoxDatacenterList) {
            for (ProxmoxNode currentNode : currentDatacenter.getProxmoxNodeArrayList()) {
                proxmoxApiVM =  webClient.get()
                        .uri("https://"+ currentDatacenter.getIp() + ":" + currentDatacenter.getPort()
                                + "/api2/json/nodes/" + currentNode.getName() +"/qemu")
                        .cookie("PVEAuthCookie", currentDatacenter.getProxmoxTicketAndToken().getTicket())
                        .header("CSRFPreventionToken", currentDatacenter.getProxmoxTicketAndToken().getToken())
                        .retrieve()
                        .bodyToMono(ProxmoxApiVM.class)
                        .block();
                currentNode.setVmArrayList(proxmoxApiVM.getData());
            }
        }
        System.out.println(proxmoxApiVM);
        return proxmoxDatacenterList;
    }
    @GetMapping("/getVMList")
    public String getVMList() {
        String responseJson = "";
        for (ProxmoxNodeName.Data node: proxmoxNodeName.getData()) {
            responseJson +=  webClient.get()
                    .uri("https://172.16.1.5:8006/api2/json/nodes/dev11/qemu")
                    .cookie("PVEAuthCookie", proxmoxTicketAndToken.getTicket())
                    .header("CSRFPreventionToken", proxmoxTicketAndToken.getToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }
        return responseJson;
    }

    @GetMapping("/getProxmoxNodeData")
    public ProxmoxNodeName getProxmoxNodeData() throws SSLException {
        proxmoxNodeName = webClient.get()
                .uri("https://172.16.1.5:8006/api2/json/cluster/status" )
                .cookie("PVEAuthCookie", proxmoxTicketAndToken.getTicket())
                .header("CSRFPreventionToken", proxmoxTicketAndToken.getToken())
                .retrieve()
                .bodyToMono(ProxmoxNodeName.class)
                .block();
        return proxmoxNodeName;
    }
    @GetMapping("/GetProxmoxTicket")
    public ProxmoxTicketAndToken getProxmoxTicket() throws SSLException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "apiuser@pve");
        formData.add("password", "Qwerty!23");
        System.out.println(proxmoxCredentials.getProxmoxServers().get(0));

        proxmoxTicketAndToken = webClient
                .post()
                .uri("https://172.16.1.5:8006/api2/json/access/ticket")
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .block()
                .bodyToMono(ProxmoxTicketAndToken.class)
                .block();
        return proxmoxTicketAndToken;
    }
}
