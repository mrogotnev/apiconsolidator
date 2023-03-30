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
    private ProxmoxFirstAPIData proxmoxFirstAPIData;
    private ProxmoxCredentials proxmoxCredentials;

    @GetMapping("/GetProxmoxAllTickets")
    public ArrayList<ProxmoxFirstAPIData> getProxmoxAllTickets() {
        ArrayList<ProxmoxFirstAPIData> proxmoxFirstAPIDataList = new ArrayList<>();
        for (ProxmoxCredentials.ProxmoxServer currentServer : proxmoxCredentials.getProxmoxServers() ) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("username", currentServer.getLogin());
            formData.add("password", currentServer.getPassword());

            proxmoxFirstAPIData = webClient
                    .post()
                    .uri("https://"+ currentServer.getIp() + ":" + currentServer.getPort() + "/api2/json/access/ticket")
                    .body(BodyInserters.fromFormData(formData))
                    .exchange()
                    .block()
                    .bodyToMono(ProxmoxFirstAPIData.class)
                    .block();
            proxmoxFirstAPIDataList.add(proxmoxFirstAPIData);
        }
        return proxmoxFirstAPIDataList;
    }
    @GetMapping("/GetProxmoxTicket")
    public ProxmoxFirstAPIData getProxmoxTicket() throws SSLException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "root@pam");
        formData.add("password", "PC1835-inv");
        System.out.println(proxmoxCredentials.getProxmoxServers().get(0));

        proxmoxFirstAPIData = webClient
                .post()
                .uri("https://172.16.3.2:8006/api2/json/access/ticket")
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .block()
                .bodyToMono(ProxmoxFirstAPIData.class)
                .block();
        return proxmoxFirstAPIData;
    }

    @GetMapping("/getProxData")
    public String getProxData() throws SSLException {
        String responseJson = webClient.get()
                .uri("https://172.16.3.2:8006/api2/json/cluster/status")
                .cookie("PVEAuthCookie", proxmoxFirstAPIData.getTicket())
                .header("CSRFPreventionToken", proxmoxFirstAPIData.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseJson;
    }

    @GetMapping("getVMList")
    public String getVMList() {
        String responseJson = webClient.get()
                .uri("https://172.16.3.2:8006/api2/json/pools/")
                .cookie("PVEAuthCookie", proxmoxFirstAPIData.getTicket())
                .header("CSRFPreventionToken", proxmoxFirstAPIData.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseJson;
    }
}
