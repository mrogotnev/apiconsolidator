package com.mrogotnev.ApiConsolidator.clients.selectel;

import com.mrogotnev.ApiConsolidator.clients.selectel.mappers.SelectelMapper;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;

@AllArgsConstructor
@Data
@Component
public class SelectelService {
    private SelectelCredentials selectelCredentials;
    private final WebClient webClientWithoutSSL;
    private SelectelMapper selectelMapper;
    private HashSet<PojoVM> selectelPojoVM;

    public String getSelectelToken() {
        ResponseEntity<String> response = webClientWithoutSSL
                .post()
                .uri(selectelCredentials.getLoginUrl() + "/cloudapi/1.0.0/sessions")
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

    public SelectelApiVMData getVMList() {
        return webClientWithoutSSL
                .get()
                .uri(selectelCredentials.getLoginUrl() + "/api/query?type=vm&filter=isVAppTemplate==false&fields=name,numberOfCpus,memoryMB,totalStorageAllocatedMb,status")
                .headers(httpHeaders -> {
                    httpHeaders.set("Accept", "application/*+json;version=" + selectelCredentials.getApiVersion());
                    httpHeaders.setBearerAuth(getSelectelToken());
                })
                .retrieve()
                .bodyToMono(SelectelApiVMData.class)
                .block();
    }

    public HashSet<PojoVM> getSelectelPojoVM() {
        if (selectelCredentials.getLoginUrl() != null) {
            for (SelectelApiVMData.SelectelApiVm currentVM : getVMList().getRecord()) {
                PojoVM pojoVM = selectelMapper.selectelApiVMToPojo(currentVM);
                selectelPojoVM.add(pojoVM);
            }
        }
        return selectelPojoVM;
    }
}
