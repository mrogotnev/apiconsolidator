package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Data
@Component
public class ProxmoxFirstAPIData {
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(value = { "cap", "username" })
    private static class Data {
        @JsonProperty("CSRFPreventionToken")
        private String token;
        private String ticket;
    }

    public String getToken() {
        return data.getToken();
    }

    public String getTicket() {
        return data.getTicket();
    }
}
