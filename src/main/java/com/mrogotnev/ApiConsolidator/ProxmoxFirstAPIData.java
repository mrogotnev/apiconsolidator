package com.mrogotnev.ApiConsolidator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.HashSet;

@Data
@Builder
@Jacksonized
public class ProxmoxFirstAPIData {
    private Data data;

    @lombok.Data
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(value = { "cap", "username" })
    private static class Data {
        @JsonProperty("CSRFPreventionToken")
        private String token;
        private String ticket;
    }


}
