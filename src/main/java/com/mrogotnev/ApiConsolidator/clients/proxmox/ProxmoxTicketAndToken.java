package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProxmoxTicketAndToken {
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
