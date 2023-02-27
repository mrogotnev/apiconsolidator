package com.mrogotnev.ApiConsolidator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        private String CSRFPreventionToken;
        private String ticket;
    }


}
