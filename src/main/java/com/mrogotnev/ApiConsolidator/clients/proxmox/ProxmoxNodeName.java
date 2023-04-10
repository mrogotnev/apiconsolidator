package com.mrogotnev.ApiConsolidator.clients.proxmox;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class ProxmoxNodeName {

    private ArrayList<Data> data;

    @lombok.Data
    @JsonIgnoreProperties(value = {"online", "nodeid", "ip", "local", "id", "type", "level"})
    static class Data {
        private String name;
    }
}
