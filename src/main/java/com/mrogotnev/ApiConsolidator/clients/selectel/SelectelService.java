package com.mrogotnev.ApiConsolidator.clients.selectel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Data
@Component
public class SelectelService {
    private SelectelCredentials selectelCredentials;
    private final WebClient webClientWithoutSSL;

    public String getSelectelToken() {
        ResponseEntity<String> response = webClientWithoutSSL.post()
                .uri(selectelCredentials.getLoginUrl())
                .headers(httpHeaders -> {
                    httpHeaders.set("Accept", "application/*;version=" + selectelCredentials.getApiVersion());
                    httpHeaders.setBasicAuth(selectelCredentials.getUsername() + "@" + selectelCredentials.getTenantName(),
                            selectelCredentials.getPassword());
                })
                .retrieve()
                .toEntity(String.class)
                .block();
        return response.getHeaders().getFirst("X-VMWARE-VCLOUD-ACCESS-TOKEN");
    }
}
